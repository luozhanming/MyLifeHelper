package cn.luozhanming.github.net

import cn.luozhanming.github.vo.Event
import cn.luozhanming.library.common.AppConfig
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface UserService {


    /**
     * 获取token
     * */
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    fun getAccessToken(
        @Field("client_id") id: String,
        @Field("client_secret") secret: String,
        @Field("code") code: String
    ): Observable<ResponseBody>


    @GET("/notifications")
    fun getNotifications(
        @Query("all") all: Boolean = true,
        @Query("since") since: String,
        @Query("before") before: String,
        @Query("participating") participating: Boolean = false
    ): Observable<ResponseBody>


    /**
     * 查看动态信息
     * @param user 用户名
     * @param page
     * @param per_page
     */

    @GET("users/{user}/received_events")
    fun getDynamicInfo(
        @Path("user") user: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = AppConfig.PER_PAGE
    ): Observable<List<Event>>


}