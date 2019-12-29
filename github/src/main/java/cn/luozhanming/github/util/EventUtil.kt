package cn.luozhanming.github.util

import cn.luozhanming.github.vo.Event

/**
 * Event工具类
 * */
object EventUtil {

    const val TYPE_ISSUES = "IssuesEvent"
    const val TYPE_PUSH = "PushEvent"
    const val TYPE_WATCH = "WatchEvent"
    const val TYPE_FORK = "ForkEvent"


    /**
     * 用于格式化动态的信息动作
     * */
    @JvmStatic
    fun formatEventAtionText(event:Event):String =
        when(event.type){
            TYPE_FORK ->"${event.actor?.displayLogin} forked ${event.repo?.name}"
            TYPE_PUSH->"${event.actor?.displayLogin} pushed ${event.repo?.name}"
            TYPE_WATCH->"${event.actor?.displayLogin} ${event.payload?.action} ${event.repo?.name}"
            TYPE_ISSUES->"${event.actor?.displayLogin} ${event.payload?.action} ${event.repo?.name}"
            else->""
        }



}