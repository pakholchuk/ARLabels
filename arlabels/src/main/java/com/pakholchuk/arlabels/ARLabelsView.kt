package com.pakholchuk.arlabels

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.pakholchuk.arlabels.data.PermissionResult
import com.pakholchuk.arlabels.databinding.ArLabelsLayoutBinding
import com.pakholchuk.arlabels.di.ARLabelsComponent
import com.pakholchuk.arlabels.di.ARLabelsDependencyProvider
import com.pakholchuk.arlabels.di.DaggerARLabelsComponent
import com.pakholchuk.arlabels.ui.Labels
import com.pakholchuk.arlabels.utils.ARLabelUtils
import com.pakholchuk.arlabels.utils.ARLabelUtils.TAG


@Suppress("UnusedPrivateMember", "TooManyFunctions")
class ARLabelsView : FrameLayout, LifecycleObserver {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, attributeSetId: Int) : super(
        context,
        attrs,
        attributeSetId
    )

    private val binding: ArLabelsLayoutBinding =
        ArLabelsLayoutBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var viewModel: IARLabelsViewModel
    private lateinit var arLabelsComponent: ARLabelsComponent

    private var onLabelClick: ((pointId: String) -> Unit)? = null
    private var label: @Composable ((LabelProperties) -> Unit)? = null

    fun onCreate(arLabelsDependencyProvider: ARLabelsDependencyProvider) {
        arLabelsComponent =
            DaggerARLabelsComponent.factory().create(arLabelsDependencyProvider)
        viewModel = arLabelsComponent.arLabelsViewModel()
        arLabelsComponent.arLabelsDependencyProvider().getARViewLifecycleOwner()
            .lifecycle.addObserver(this)
        checkPermissions()
    }

//    fun setUpLabelsView(
//        labelsDataList: List<ARLabelData>,
//        onLabelClick: ((pointId: String) -> Unit)? = null,
//        label: @Composable ((labelProperties: LabelProperties, modifier: Modifier) -> Unit)? = null
//    ) {
//        viewModel.setARLabelData(labelsDataList)
//        this.onLabelClick = onLabelClick
//        this.label = label
//    }

    var labelsDataList: List<ARLabelData> = listOf()
        set(value) {
            field = value
            viewModel.setARLabelData(value)
        }

    fun setOnLabelClickListener(onLabelClick: (pointId: String) -> Unit) {
        this.onLabelClick = onLabelClick
    }

    fun setComposableLabel(label: @Composable (labelProperties: LabelProperties) -> Unit) {
        this.label = label
    }

    fun setAndroidViewLabel(onCreateViewHolder: (context: Context) -> ViewHolder) {
        @Composable
        fun AndroidViewLabel(labelProperties: LabelProperties) {
            val context = AmbientContext.current
            val holder = remember {
                onCreateViewHolder(context)
            }
            AndroidView(viewBlock = { holder.view }) {
                holder.onBindView(labelProperties)
            }
        }
        label = { lp -> AndroidViewLabel(lp) }
    }

    private fun checkPermissions() {
        viewModel.permissionState.observe(
            arLabelsComponent.arLabelsDependencyProvider().getARViewLifecycleOwner(),
            Observer { permissionState ->
                when (permissionState) {
                    PermissionResult.GRANTED -> {
                        startCamera()
                        observeCompassState()
                    }
                    PermissionResult.SHOW_RATIONALE -> showRationaleSnackbar()
                    PermissionResult.NOT_GRANTED -> Unit
                }
            })
        viewModel.checkPermissions()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val preview = androidx.camera.core.Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    arLabelsComponent.arLabelsDependencyProvider().getARViewLifecycleOwner(),
                    cameraSelector, preview
                )
            } catch (e: Exception) {
                Log.e(TAG, "Use case binding failed", e)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    private fun observeCompassState() {
        binding.compose.setContent {
            val labelsState = viewModel.compassUpdate.map { compassData ->
                ARLabelUtils.prepareLabelsProperties(
                    compassData,
                    binding.viewFinder.width,
                    binding.viewFinder.height
                )
            }.observeAsState(initial = listOf())
            labelsState.value.find { it.positionX > 0 && it.positionX < binding.viewFinder.width }
                ?.let {
                    viewModel.setLowPassFilterAlpha(
                        ARLabelUtils.adjustLowPassFilterAlphaValue(
                            it.positionX.toFloat(),
                            binding.viewFinder.width
                        )
                    )
                }
            Labels(labels = labelsState.value, onLabelClick = onLabelClick, label = label)
        }
        viewModel.getUpdates()

    }

    private fun showErrorDialog(message: String) {
//        AlertDialog.Builder(context)
//            .setTitle(R.string.error_title)
//            .setMessage(resources.getString(R.string.error_message, message))
//            .setPositiveButton(android.R.string.ok) { _, _ ->
//                observeCompassState()
//            }
//            .setNegativeButton(android.R.string.cancel) { _, _ ->
//
//            }
//            .setIcon(android.R.drawable.ic_dialog_alert)
//            .show()
    }

    fun onRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        viewModel.onRequestPermissionResult(requestCode, permissions, grantResults)
    }

    private fun showRationaleSnackbar() {
        Snackbar.make(
            this,
            R.string.essential_permissions_not_granted_info,
            Snackbar.LENGTH_SHORT
        )
            .setAction(R.string.permission_recheck_question) { viewModel.checkPermissions() }
            .setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE)
            .show()
    }

}
