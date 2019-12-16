package cn.luozhanming.library.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


object GsonUtil {

    private val mGson: Gson = GsonBuilder().create()

    fun <T> parseJson(json: String, clazz: Class<T>): T {
        return mGson.fromJson(json, clazz)
    }

    fun <T> toJson(obj: T): String = mGson.toJson(obj)

    fun <T> parseJsonToArray(json: String, clazz: Class<T>): List<T> {
        val founderListType = object : TypeToken<ArrayList<T>>() {}.type
        return mGson.fromJson(json, founderListType)
    }


}