package cn.luozhanming.github.repository

import cn.luozhanming.LoginUserQuery
import cn.luozhanming.fragment.UserObject
import cn.luozhanming.github.di.GithubScope
import cn.luozhanming.github.net.NotificationsService
import cn.luozhanming.github.net.rxQuery
import cn.luozhanming.library.common.checkUnexpectNetResponse
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.text.DateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@GithubScope
class UserRepository @Inject constructor(
    private val apolloClient: ApolloClient,
    private val retrofit: Retrofit,
    private val iso8601Format: DateFormat
) {

    fun getLoginViewer(): Observable<UserObject?> {
        val query = LoginUserQuery()
        return apolloClient.rxQuery(query)
            .map { t: Response<LoginUserQuery.Data> ->
                t.data()?.viewer()?.fragments()?.userObject()
            }.doOnNext {
                checkUnexpectNetResponse(it)
            }
    }


    fun getNotifacations(): Observable<ResponseBody> {
        return retrofit.create(NotificationsService::class.java)
            .loadNotifications(
                true,
//                iso8601Format.format(Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000)),
//                iso8601Format.format(Date(System.currentTimeMillis())),
                false
            )
            .map { body ->
                val string = body.string()
                body
            }
    }
}

