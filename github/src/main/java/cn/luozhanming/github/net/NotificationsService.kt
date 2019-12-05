package cn.luozhanming.github.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 通知服务
 * */
interface NotificationsService {
    /**
     * 获取本人所有通知
     * */
    @GET("/notifications")
    fun loadNotifications(
        @Query("all") all: Boolean = true,
//        @Query("since") since: String,
//        @Query("before") before: String,
        @Query("participating") participating: Boolean = false
    ): Observable<ResponseBody>


    @GET("/repo/{owner}/{repo}/notifications")
    fun loadNotificationsOnRepo(@Path("owner") owner:String,
                                @Path("repo") repo:String,
                                @Query("all") all:Boolean = true,
                                @Query("since") since:String,
                                @Query("before") before:String,
                                @Query("participating") participating: Boolean)


}