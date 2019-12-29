package cn.luozhanming.github.repository

import cn.luozhanming.LoginUserQuery
import cn.luozhanming.fragment.UserObject
import cn.luozhanming.github.di.GithubScope
import cn.luozhanming.github.net.UserService
import cn.luozhanming.github.net.rxQuery
import cn.luozhanming.github.util.EventUtil
import cn.luozhanming.github.vo.*
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
    fun getDynamicInfo(page: Int): Observable<Pager<Event>> {
        return retrofit.create(UserService::class.java)
            .getDynamicInfo(
                UserLogin.getUsername() ?: throw NullPointerException("Username cannot be null."),
                page
            ).doOnNext {
                checkUnexpectNetResponse(it)
                it.asSequence().filter {
                    EventUtil.TYPE_WATCH.equals(it.type) or EventUtil.TYPE_FORK.equals(it.type) or
                            EventUtil.TYPE_ISSUES.equals(it.type) or EventUtil.TYPE_PUSH.equals(it.type)
                }
            }.map {
                if (!it.isEmpty()) {//不为空就是加载成功
                    return@map Pager(page, PAGE_STATE_SUCCESS, it)
                } else {   //为空就是加载失败或者没有更多
                    return@map Pager(page, PAGE_STATE_NO_MORE, it)
                }
            }
    }
}

