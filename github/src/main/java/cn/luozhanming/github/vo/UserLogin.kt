package cn.luozhanming.github.vo

import android.text.TextUtils
import cn.luozhanming.github.common.GithubPreference
import cn.luozhanming.library.common.AppConfig

/**
 * 用户登录状态保存
 * */
object UserLogin {

    private var userName: String? by GithubPreference(AppConfig.PREF_USERNAME, "")
    private var accessToken: String? by GithubPreference(AppConfig.PREF_TOKEN, "")


    fun login(accessToken: String) {
        this.accessToken = accessToken
    }

    fun logout() {
        this.accessToken = ""
    }

    fun isLogin() = !TextUtils.isEmpty(accessToken)

    fun getToken() = accessToken

    fun getUsername() = userName

    fun setUsername(name: String?) {
        userName = name?:""
    }


}