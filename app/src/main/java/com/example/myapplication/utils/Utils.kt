package com.example.myapplication.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import com.example.myapplication.R
import com.example.myapplication.application.BaseApplication

class Utils {
    companion object {
        @JvmStatic
        fun showErrorDialog(context: Context?, error: String) {
            val builder = context?.let { AlertDialog.Builder(it) }

            builder?.setTitle(BaseApplication.instance.getString(R.string.dialog_title_error))
            builder?.setMessage(error)
            builder?.setPositiveButton(BaseApplication.instance.getString(R.string.dialog_positive_response)) {
                    dialog, _ -> dialog.dismiss()
            }

            val dialog: AlertDialog? = builder?.create()
            dialog?.setCancelable(false)

            dialog?.show()
        }
    }
}