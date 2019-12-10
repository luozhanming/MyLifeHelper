package cn.luozhanming.library.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtil {

    private val mGson: Gson = GsonBuilder().create()

    fun <T> parseJson(json: String, clazz: Class<T>): T {
        return mGson.fromJson(json, clazz)
    }
}