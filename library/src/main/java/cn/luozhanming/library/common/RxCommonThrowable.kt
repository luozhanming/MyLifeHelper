package cn.luozhanming.library.common

import cn.luozhanming.library.BuildConfig
import io.reactivex.functions.Consumer

open class RxCommonThrowable : Consumer<Throwable> {
    override fun accept(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
        } else {
            LoggerUtil.printThrowable(t)
        }
    }
}