package cn.luozhanming.library

import android.app.Application
import cn.luozhanming.library.common.LoggerUtil
import cn.luozhanming.library.di.CommonComponent
import cn.luozhanming.library.di.DaggerCommonComponent
import kotlin.properties.Delegates

class LifeHelperApp : Application() {

    companion object {
        var instance: LifeHelperApp by Delegates.notNull()
    }

    private lateinit var mCommonComponent: CommonComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        mCommonComponent = DaggerCommonComponent.builder().application(this).build()
    }

    fun getCommonComponent() = mCommonComponent
}