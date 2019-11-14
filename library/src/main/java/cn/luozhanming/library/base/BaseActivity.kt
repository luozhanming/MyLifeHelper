package cn.luozhanming.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.luozhanming.library.common.ScreenCompatUtil

abstract class BaseActivity : AppCompatActivity(){

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenCompatUtil.initScreenCompatBaseWidth(this,application)
    }


}