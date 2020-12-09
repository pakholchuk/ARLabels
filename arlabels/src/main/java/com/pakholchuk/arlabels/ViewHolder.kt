package com.pakholchuk.arlabels

import android.view.View

abstract class ViewHolder (open val view: View) {
    abstract fun onBindView(labelProperties: LabelProperties)
}