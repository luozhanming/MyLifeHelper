package cn.luozhanming.github.net

import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GithubService {


    /**
     * 获取token
     * */
    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    fun loadAccessToken(
        @Field("client_id") id: String,
        @Field("client_secret") secret: String,
        @Field("code") code: String
    ): Flowable<String>

}