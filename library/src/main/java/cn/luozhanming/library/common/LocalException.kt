package cn.luozhanming.library.common

/**
 * 网络返回Null异常
 * */
class UnexpectNetResponseException : Throwable("Unexpect net response.")

/**
 * 检查网络返回Null异常
 * */
fun <T> checkUnexpectNetResponse(item:T){
    item?:throw UnexpectNetResponseException()
}