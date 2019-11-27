package cn.luozhanming.library.ext

import android.app.Activity
import android.view.WindowManager

fun Activity.setFullScreen(fullScreen: Boolean) = if (fullScreen)
    window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
else
    window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

