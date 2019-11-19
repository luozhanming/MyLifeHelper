package cn.luozhanming.github.repository

import cn.luozhanming.github.BuildConfig
import cn.luozhanming.github.di.GithubScope
import cn.luozhanming.github.net.GithubService
import cn.luozhanming.github.vo.AccessToken
import cn.luozhanming.library.common.AppExecutor
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@GithubScope
class LoginRepository @Inject constructor(
    private val appExecutor: AppExecutor,
    private val api: GithubService
) {

    var tokenStorage: AccessToken? = null
        get() = field
        set(value) {
            field = value
        }

    companion object {
        /**
         * 生成登录OauthUrl用于WebView访问
         * @param username 用户名
         * @param password 密码
         * */
        fun generateLoginOAuthUrl(username: String, password: String): String {
            val login = "$username:$password"
            val scope =
                "repo%20admin:repo_hook%20admin:org%20admin:public_key%20admin:org_hook%20gist" +
                        "%20notifications%20user%20delete_repo%20write:discussion%20write:packages" +
                        "%20read:packages%20delete:packages%20admin:gpg_key%20workflow"
            return "https://github.com/login/oauth/authorize?client_id=${BuildConfig.CLIENT_ID}" +
                    "&login=${login}&scope=${scope}"
        }
    }

    /**
     * 获取accessToken
     * */
    fun getAccessToken(code: String): Observable<AccessToken> {
        return api.loadAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code)
            .map { body ->
                val it = body.string()
                val split = it.split("&")
                var token: String? = null
                var type: String? = null
                split.forEach {
                    val split1 = it.split("=")
                    when (split1[0]) {
                        "access_token" -> token = split1[1]
                        "token_type" -> type = split1[1]
                    }
                }
                AccessToken(token, type)
            }.subscribeOn(Schedulers.io())
    }


}