package cn.luozhanming.library.ext

import androidx.fragment.app.Fragment
import cn.luozhanming.library.base.BaseActivity

fun Fragment.getBaseActivity():BaseActivity?{
    val base = activity as? BaseActivity
    return base
}