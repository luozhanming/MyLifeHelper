package cn.luozhanming.github.repository

import cn.luozhanming.LoginUserQuery
import cn.luozhanming.fragment.UserObject
import cn.luozhanming.github.di.GithubScope
import cn.luozhanming.github.net.UserService
import cn.luozhanming.github.net.rxQuery
import cn.luozhanming.github.vo.Event
import cn.luozhanming.github.vo.UserLogin
import cn.luozhanming.library.common.checkUnexpectNetResponse
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import io.reactivex.Observable
import retrofit2.Retrofit
import java.text.DateFormat
import javax.inject.Inject

@GithubScope
class UserRepository @Inject constructor(
    private val apolloClient: ApolloClient,
    private val retrofit: Retrofit,
    private val iso8601Format: DateFormat
) {

    /**
     * 获取当前登录用户信息
     * */
    fun getLoginViewer(): Observable<UserObject?> {
        val query = LoginUserQuery()
        return apolloClient.rxQuery(query)
            .map { t: Response<LoginUserQuery.Data> ->
                t.data()?.viewer()?.fragments()?.userObject()
            }.doOnNext {
                checkUnexpectNetResponse(it)
            }
    }

    /**
     * 获取当前用户动态接收信息
     * */
    fun getDynamicInfo(page: Int): Observable<List<Event>> {
        return retrofit.create(UserService::class.java)
            .getDynamicInfo(
                UserLogin.getUsername() ?: throw NullPointerException("Username cannot be null."),
                page
            ).doOnNext {
                checkUnexpectNetResponse(it)
                it.asSequence().filter {
                    "WatchEvent".equals(it.type) or "ForkEvent".equals(it.type)
                }
            }
    }
}

