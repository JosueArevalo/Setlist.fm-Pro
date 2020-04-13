package com.josuearevalodev.setlistfmpro.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.setlistfmpro.R

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.view_loading, this)
        gone()
    }
}
