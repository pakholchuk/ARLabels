package com.pakholchuk.arlabels.adapter

import android.view.View
import com.pakholchuk.arlabels.LabelProperties

abstract class LabelViewHolder(open val view: View) {
    abstract fun bind(labelProperties: LabelProperties, listPosition: Int)
}