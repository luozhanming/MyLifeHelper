package cn.luozhanming.github.common

import android.content.Context
import cn.luozhanming.library.LifeHelperApp
import cn.luozhanming.library.common.AppConfig
import kotlin.reflect.KProperty

/**
 * Github模块的SharePreference
 * */
class GithubPreference<T>(private val keyName: String, private val default: T) {

    companion object {
        val pref =
            LifeHelperApp.instance.getSharedPreferences(AppConfig.GITHUB_SP, Context.MODE_PRIVATE)

        fun <T> putElement(keyName: String, value: T) {
            pref.edit().apply {
                when (value) {
                    is String -> putString(keyName, value)
                    is Boolean -> putBoolean(keyName, value)
                    is Int -> putInt(keyName, value)
                    is Long -> putLong(keyName, value)
                    is Float -> putFloat(keyName, value)
                }
            }.apply()
        }

        fun <T> getElement(keyName: String, default: T): T = with(pref) {
            val res: Any = when (default) {
                is String -> getString(keyName, default)
                is Boolean -> getBoolean(keyName, default)
                is Int -> getInt(keyName, default)
                is Long -> getLong(keyName, default)
                is Float -> getFloat(keyName, default)
                else -> throw IllegalArgumentException("Type Error, cannot be saved!")
            }
            return res as T
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreferences(keyName, default)
    }


    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharePreferences(keyName, value)
    }

    private fun putSharePreferences(keyName: String, value: T) {
        pref.edit().apply {
            when (value) {
                is String -> putString(keyName, value)
                is Boolean -> putBoolean(keyName, value)
                is Int -> putInt(keyName, value)
                is Long -> putLong(keyName, value)
                is Float -> putFloat(keyName, value)
            }
        }.apply()
    }

    private fun getSharePreferences(keyName: String, default: T): T = with(pref) {
        val res: Any = when (default) {
            is String -> getString(keyName, default)
            is Boolean -> getBoolean(keyName, default)
            is Int -> getInt(keyName, default)
            is Long -> getLong(keyName, default)
            is Float -> getFloat(keyName, default)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
        return res as T
    }


}