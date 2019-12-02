package cn.luozhanming.github.repository

import cn.luozhanming.LoginUserQuery
import cn.luozhanming.github.di.GithubScope
import cn.luozhanming.github.net.rxQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import io.reactivex.Observable
import javax.inject.Inject

@GithubScope
class UserRepository @Inject constructor(private val apolloClient: ApolloClient) {

    fun getLoginViewer(): Observable<LoginUserQuery.Viewer> {
        val query = LoginUserQuery()
        return apolloClient.rxQuery(query)
            .map { t: Response<LoginUserQuery.Data> ->
                t.data()?.viewer()
            }

    }

}