package cn.luozhanming.github.repository

import cn.luozhanming.LoginUserQuery
import cn.luozhanming.fragment.UserObject
import cn.luozhanming.github.di.GithubScope
import cn.luozhanming.github.net.rxQuery
import cn.luozhanming.library.common.UnexpectNetResponseException
import cn.luozhanming.library.common.checkUnexpectNetResponse
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import io.reactivex.Observable
import javax.inject.Inject

@GithubScope
class UserRepository @Inject constructor(private val apolloClient: ApolloClient) {

    fun getLoginViewer(): Observable<UserObject?> {
        val query = LoginUserQuery()
        return apolloClient.rxQuery(query)
            .map { t: Response<LoginUserQuery.Data> ->
                t.data()?.viewer()?.fragments()?.userObject()
            }.doOnNext {
                checkUnexpectNetResponse(it)
            }
    }

}