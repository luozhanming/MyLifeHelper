package cn.luozhanming.github.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    val dateFormat1: DateFormat = SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ")
    val dateFormat2: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    /**
     * 计算时间与现在的时长
     * */
    fun computeDateFromNow(dateStr: String): String {
        val date = dateFormat1.parse(dateStr)
        val nowDate = Date()
        //秒
        val time = (nowDate.time - date.time) / 1000
        when {
            time < (60 * 60) -> return "刚刚"
            time < (60 * 60 * 24) -> return "${time / 60 * 60}小时前"
            time < (60 * 60 * 24 * 3) -> return "${time / 60 * 60 * 24}天前"
            else -> return dateFormat2.format(date)
        }
    }
}