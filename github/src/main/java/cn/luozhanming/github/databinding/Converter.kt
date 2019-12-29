package cn.luozhanming.github.databinding

import androidx.databinding.InverseMethod

object Converter {
    @JvmStatic
    @InverseMethod("boxBoolean")
    fun unboxBoolean(v: Boolean?): Boolean = v ?: false

    @JvmStatic
    fun boxBoolean(v: Boolean): Boolean? = v


}