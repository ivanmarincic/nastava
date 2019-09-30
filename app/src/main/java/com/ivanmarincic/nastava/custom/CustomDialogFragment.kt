package com.ivanmarincic.nastava.custom

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import dagger.android.support.DaggerAppCompatDialogFragment


open class CustomDialogFragment(private var title: String) : DaggerAppCompatDialogFragment() {

    constructor() : this("")

    private var dismissListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        title = savedInstanceState?.getString("title", title) ?: title
        return CustomDialog(title, context)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("title", title)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }

    fun setOnDismissListener(dismissListener: () -> Unit) {
        this.dismissListener = dismissListener
    }
}