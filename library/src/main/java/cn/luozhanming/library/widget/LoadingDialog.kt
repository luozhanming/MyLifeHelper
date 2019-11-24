package cn.luozhanming.library.widget

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import cn.luozhanming.library.R
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialog) {


    companion object {
        fun showDialog(
            context: Context,
            cancelable: Boolean,
            cancelListener: DialogInterface.OnCancelListener?
        ): LoadingDialog? {
            return showDialog(context, null, cancelable, cancelListener)
        }

        fun showDialog(
            context: Context,
            message: CharSequence?,
            cancelable: Boolean,
            cancelListener: DialogInterface.OnCancelListener?
        ): LoadingDialog? {
            val dialog = LoadingDialog(context)
            dialog.setContentView(R.layout.dialog_loading)
            if (message.isNullOrBlank()) {
                dialog.tv_loading_text?.visibility = View.GONE
            } else {
                dialog.tv_loading_text?.text = message
            }
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(cancelable)
            dialog.setOnCancelListener(cancelListener)
            dialog.window?.attributes?.gravity = Gravity.CENTER
            val lp = dialog.window?.attributes
            lp?.dimAmount = 0.2f
            dialog.window?.attributes = lp
            dialog.show()
            return dialog
        }
    }

    fun show(message: CharSequence?) {
        super.show()
        tv_loading_text.text = message
    }
}
