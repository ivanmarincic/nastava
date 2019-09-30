package com.ivanmarincic.nastava.custom

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.card.MaterialCardView
import com.ivanmarincic.nastava.R
import com.ivanmarincic.nastava.databinding.IncludeDialogToolbarBinding


class CustomDialog(private val dialogTitle: String, context: Context?) :
    AppCompatDialog(context, R.style.DialogAppearance) {

    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.run {
            // Spread the dialog as large as the screen.
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun setContentView(view: View) {
        super.setContentView(wrap(view))
    }

    private fun wrap(content: View): View {
        val res = context.resources
        val verticalMargin = res.getDimensionPixelSize(R.dimen.dialog_vertical_margin)
        val horizontalMargin = res.getDimensionPixelSize(R.dimen.dialog_horizontal_margin)
        return FrameLayout(context).apply {
            val dialogToolbarBinding = IncludeDialogToolbarBinding.inflate(LayoutInflater.from(context), this, false)
            dialogToolbarBinding.title.text = this@CustomDialog.dialogTitle
            dialogToolbarBinding.closeButton.setOnClickListener {
                this@CustomDialog.cancel()
            }
            val dialogContent = MaterialCardView(context).apply {
                addView(LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    addView(dialogToolbarBinding.root)
                    addView(content)
                })
                radius = res.getDimension(R.dimen.dialog_rounded_corner)
            }
            addView(dialogContent, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin)
                gravity = Gravity.CENTER
            })
            val rect = Rect()
            setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dialogContent.getGlobalVisibleRect(rect)
                        if (!rect.contains(event.x.toInt(), event.y.toInt())) {
                            cancel()
                            true
                        } else {
                            false
                        }
                    }
                    else -> {
                        false
                    }
                }
            }
            background = ColorDrawable(ResourcesCompat.getColor(res, R.color.dialog_dim, context.theme))
        }
    }
}