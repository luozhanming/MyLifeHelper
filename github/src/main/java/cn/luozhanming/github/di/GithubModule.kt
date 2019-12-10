package cn.luozhanming.github.di

import cn.luozhanming.github.BuildConfig
import cn.luozhanming.github.net.UserService
import cn.luozhanming.github.vo.UserLogin
import cn.luozhanming.library.common.AppConfig
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
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
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                request = request.newBuilder()
                    .addHeader("Authorization", "token ${UserLogin.getToken() ?: ""}")
                    .build()
                return chain.proceed(request)
            }
        })
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(AppConfig.GITHUB_API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @GithubScope
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        val apolloClient = ApolloClient.builder()
            .serverUrl(AppConfig.GITHUB_BASE_QL_URL)
            .okHttpClient(okHttpClient)
            .build()
        return apolloClient
    }

    @GithubScope
    @Provides
    fun provideGithubService(retrofit: Retrofit) = retrofit.create(UserService::class.java)

    /**
     * 提供ISO8601时间格式
     * */
    @GithubScope
    @Provides
    fun provicdeISO8601DateFormat(): DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
}