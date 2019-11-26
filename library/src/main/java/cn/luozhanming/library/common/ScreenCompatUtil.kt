package cn.luozhanming.library.common

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity

/**
 * 今日头条屏幕适配工具类
 * */
object ScreenCompatUtil {

    private var sNonCompatDensity: Float = 0f
    private var sNonCompatScaledDensity = 0f;

    /**
     * 以宽为基准
     * */
    fun initScreenCompatBaseWidth(activity: AppCompatActivity, app: Application) {
        val appDisplayMetrics = app.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNonCompatScaledDensity = appDisplayMetrics.scaledDensity
            app.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onLowMemory() {
                }

                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScaledDensity = app.resources.displayMetrics.scaledDensity
                    }
                }
            })
        }
        val targetDensity = appDisplayMetrics.widthPixels.toFloat() / AppConfig.DESIGN_WIDTH_BASE
        val targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()

        appDisplayMetrics.densityDpi = targetDensityDpi
        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity

        val activityMetrics = activity.resources.displayMetrics
        activityMetrics.density = targetDensity
        activityMetrics.scaledDensity = targetScaledDensity
        activityMetrics.densityDpi = targetDensityDpi
    }

    /**
     * 以高为基准
     * */
    fun initScreenCompatBaseHeight(activity: AppCompatActivity, app: Application) {
        val appDisplayMetrics = app.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNonCompatScaledDensity = appDisplayMetrics.scaledDensity
            app.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onLowMemory() {
                }

                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScaledDensity = app.resources.displayMetrics.scaledDensity
                    }
                }
            })
        }
        val targetDensity = appDisplayMetrics.heightPixels.toFloat() / AppConfig.DESIGN_WIDTH_BASE
        val targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()

        appDisplayMetrics.densityDpi = targetDensityDpi
        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity

        val activityMetrics = activity.resources.displayMetrics
        activityMetrics.density = targetDensity
        activityMetrics.scaledDensity = targetScaledDensity
        activityMetrics.densityDpi = targetDensityDpi
    }
}