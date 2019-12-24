package cn.luozhanming.library.common

import cn.luozhanming.library.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

/**
 * Log工具类
 * */
object LoggerUtil {
    init {
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
        val file = File("${AppConfig.APP_EXTERN_PATH}/net_log/${System.currentTimeMillis()}.txt")
        if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            return
        }
        val writer = PrintWriter(FileWriter(file))
        throwable.printStackTrace(writer)
        writer.flush()
        writer.close()
    }


}