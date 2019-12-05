package cn.luozhanming.github.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat

interface NotificationsService {
    @GET("/notifications")
    fun loadNotifications(
        @Query("all") all: Boolean = true,
        @Query("since") since: String ,
        @Query("before") before: String,
        @Query("participating") participating: Boolean = false
    ): Observable<ResponseBody>
}