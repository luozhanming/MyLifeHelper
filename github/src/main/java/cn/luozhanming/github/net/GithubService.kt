package cn.luozhanming.github.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface GithubService {


    /**
     * 获取token
     * */
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    fun loadAccessToken(
        @Field("client_id") id: String,
        @Field("client_secret") secret: String,
        @Field("code") code: String
    ): Observable<ResponseBody>


    @GET("/notifications")
    fun loadNotifications(
        @Query("all") all: Boolean = true,
        @Query("since") since: String,
        @Query("before") before: String,
        @Query("participating") participating: Boolean = false
    ): Observable<ResponseBody>


}