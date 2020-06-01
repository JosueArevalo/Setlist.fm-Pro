package com.josuearevalodev.setlistfmpro.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.setlistfmpro.databinding.ViewErrorBinding
import com.josuearevalodev.setlistfmpro.databinding.ViewNoConnectionBinding

class NoConnectionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private var binding: ViewNoConnectionBinding
    val bRetry: MaterialButton
        get() = binding.bRetry

    init {
        binding = ViewNoConnectionBinding.inflate(LayoutInflater.from(context), this, true)
        gone()
    }
}
