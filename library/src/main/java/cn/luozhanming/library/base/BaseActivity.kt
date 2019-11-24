package cn.luozhanming.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.luozhanming.library.common.ScreenCompatUtil
import cn.luozhanming.library.common.autoCleared
import cn.luozhanming.library.widget.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initView()

    private var mLoadingDialog:LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenCompatUtil.initScreenCompatBaseWidth(this, application)
    }

    fun showLoadingDialog(message: CharSequence?) {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.showDialog(this, message, false, null)!!
        } else {
            mLoadingDialog?.show(message)
        }
    }

    fun hideLoadingDialog() {
        mLoadingDialog?.dismiss()
    }


}