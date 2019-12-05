package cn.luozhanming.library.common

import android.os.Environment

object AppConfig {
    /**网络请求超时时间*/
    const val NET_TIME_OUT = 30L

    /**GithubApiURL*/
    const val GITHUB_BASE_QL_URL = "https://api.github.com/graphql"

    const val GITHUB_API_URL = "https://api.github.com/"

    /**应用存储文件夹*/
     val APP_EXTERN_PATH =
        "${Environment.getExternalStorageDirectory().absolutePath}/MyLifeHelper"

    /**应用界面设计宽度基准，单位dp*/
    const val DESIGN_WIDTH_BASE = 360




    /********************************Github******************************/

    const val GITHUB_SP = "GithubSP"
    const val PREF_USERNAME = "username"
    const val PREF_AUTO_LOGIN = "autoLogin"
    const val PREF_TOKEN = "token"
}