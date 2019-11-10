package cn.luozhanming.library

import android.app.Application
import cn.luozhanming.library.di.CommonComponent
import cn.luozhanming.library.di.DaggerCommonComponent

class LifeHelperApp : Application() {

    private lateinit var mCommonComponent: CommonComponent

    override fun onCreate() {
        super.onCreate()
        mCommonComponent = DaggerCommonComponent.builder().application(this).build()
    }

    fun getCommonComponent() = mCommonComponent
}