package cn.luozhanming.github.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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

}