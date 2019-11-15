package cn.luozhanming.github.vo

/**
 * 用户登录状态保存
 * */
object UserLogin {

    private var accessToken: AccessToken? = null


    fun login(accessToken: AccessToken) {
        this.accessToken = accessToken
    }

    fun logout() {
        this.accessToken = null
    }

    fun isLogin() = accessToken == null

}