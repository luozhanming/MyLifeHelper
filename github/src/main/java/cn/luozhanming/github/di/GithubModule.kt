package cn.luozhanming.github.di

import cn.luozhanming.github.BuildConfig
import cn.luozhanming.github.net.GithubService
import cn.luozhanming.library.common.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class GithubModule {

    @GithubScope
    @Provides
    fun proviceOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.connectTimeout(AppConfig.NET_TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(AppConfig.NET_TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(AppConfig.NET_TIME_OUT, TimeUnit.SECONDS)
//        builder.addInterceptor(object : Interceptor {
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val request = chain.request()
//                val re
//                request.newBuilder()
//                    .addHeader("Authorization", "bearer ${BuildConfig.ACCESS_TOKEN}")
//                    .post(request.body)
//                    .build()
//                retur ·n chain.proceed(request)
//            }
//        })
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        builder.addInterceptor(logging)
        return builder.build()
    }

    @GithubScope
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(proviceOkHttpClient())
        .baseUrl(AppConfig.GITHUB_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @GithubScope
    @Provides
    fun provideGithubService() = provideRetrofit().create(GithubService::class.java)
}