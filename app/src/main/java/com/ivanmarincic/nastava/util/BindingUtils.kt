package com.ivanmarincic.nastava.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.ivanmarincic.nastava.R
import kotlin.math.roundToInt


@BindingAdapter("textCompat")
fun setFloat(view: AppCompatTextView, value: String?) {
    if (value != null) {
        view.text = value
    } else {
        view.text = ""
    }
}

@BindingAdapter("textCompat")
fun setFloat(view: AppCompatTextView, value: Float?) {
    if (value != null) {
        view.text = value.toString()
    } else {
        view.text = ""
    }
}

@BindingAdapter("textCompat")
fun setInt(view: AppCompatTextView, value: Int?) {
    if (value != null) {
        view.text = value.toString()
    } else {
        view.text = ""
    }
}

@BindingAdapter("textCompat")
fun setLong(view: AppCompatTextView, value: Long?) {
    if (value != null) {
        view.text = value.toString()
    } else {
        view.text = ""
    }
}

@BindingAdapter("android:progress")
fun setProgress(view: ProgressBar, value: Float?) {
    if (value != null) {
        view.progress = value.roundToInt()
    } else {
        view.progress = 0
    }
}