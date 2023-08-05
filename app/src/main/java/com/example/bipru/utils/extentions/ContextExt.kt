package com.example.bipru.utils.extentions

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.bipru.R

fun Context?.alert(
    title: String,
    positive: (() -> Unit)? = null,
    positiveButtonResId: Int = R.string.button_ok,
    negative: (() -> Unit)? = null,
    negativeButtonResId: Int = R.string.button_cancel,
    cancelable: Boolean = true,
    canceledOnTouchOutside: Boolean = true
) {
    this?.let {
        val builder = AlertDialog.Builder(this, R.style.DialogTheme)
            .setTitle(title)
            .setPositiveButton(positiveButtonResId) { _, _ -> positive?.invoke() }
            .setCancelable(cancelable)
            .setNegativeButton(negativeButtonResId) { _, _ -> negative?.invoke() }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside)
        dialog.show()
    }
}