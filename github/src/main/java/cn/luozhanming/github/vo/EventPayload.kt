package cn.luozhanming.github.vo

data class EventPayload(val action:String?,
                   val forkee:Repository?,
                   val body:String?)