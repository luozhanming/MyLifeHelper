package cn.luozhanming.library.common

import android.os.Environment

object AppConfig {
    /**网络请求超时时间*/
    val NET_TIME_OUT = 30L

    /**GithubApiURL*/
    val GITHUB_BASE_URL = "https://api.github.com/graphql/"

    /**应用存储文件夹*/
    val APP_EXTERN_PATH =
        "${Environment.getExternalStorageDirectory().absolutePath}/MyLifeHelper"

    /**应用界面设计宽度基准，单位dp*/
    val DESIGN_WIDTH_BASE = 360
}