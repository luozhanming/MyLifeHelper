package cn.luozhanming.library.common

import android.os.Environment
import cn.luozhanming.library.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

object LoggerUtil {


    fun init() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun i(message: String) {
        Logger.i(message)
    }

    fun d(message: String) {
        Logger.d(message)
    }

    fun e(message: String) {
        Logger.e(message)
    }

    fun printThrowable(throwable: Throwable) {
        val file = File("${AppConfig.APP_EXTERN_PATH}/log/${System.currentTimeMillis()}.txt")
        if (!file.exists() && !file.mkdirs()) {
            return
        }
        val writer = PrintWriter(FileWriter(file))
        throwable.printStackTrace(writer)
    }


}