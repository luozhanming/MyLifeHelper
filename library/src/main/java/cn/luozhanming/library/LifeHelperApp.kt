package cn.luozhanming.library

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import cn.luozhanming.library.common.AppConfig
import cn.luozhanming.library.common.LoggerUtil
import cn.luozhanming.library.di.CommonComponent
import cn.luozhanming.library.di.DaggerCommonComponent

class LifeHelperApp : Application() {

    private lateinit var mCommonComponent: CommonComponent

    override fun onCreate() {
        super.onCreate()
        mCommonComponent = DaggerCommonComponent.builder().application(this).build()
        LoggerUtil.init()
    }
    fun getCommonComponent() = mCommonComponent
}