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
        initScreenCompat()
        LoggerUtil.init()
    }


    //今日头条屏幕适配方案
    private fun initScreenCompat() {
        //以宽为360dp适配
        val screenWidth = resources.displayMetrics.widthPixels
        val ratio = AppConfig.DESIGN_WIDTH_BASE.toFloat() / screenWidth
        val targetDensity = ratio * resources.displayMetrics.density
        val targetScaleDensity =
            targetDensity * (resources.displayMetrics.scaledDensity / resources.displayMetrics.density)
        resources.displayMetrics.density = targetDensity
        resources.displayMetrics.scaledDensity = targetScaleDensity
        registerComponentCallbacks(object : ComponentCallbacks {
            override fun onLowMemory() {
            }

            override fun onConfigurationChanged(newConfig: Configuration?) {
                if (newConfig != null && newConfig.fontScale > 0) {
                    val targetScaleDensity =
                        targetDensity * (resources.displayMetrics.scaledDensity / resources.displayMetrics.density)
                    resources.displayMetrics.scaledDensity = targetScaleDensity
                }
            }

        })
    }

    fun getCommonComponent() = mCommonComponent
}