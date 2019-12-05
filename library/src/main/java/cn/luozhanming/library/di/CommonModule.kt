package cn.luozhanming.library.di

import android.app.Application
import android.content.res.Resources
import cn.luozhanming.library.common.AppExecutor
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Singleton


@Module
class CommonModule {

    @Singleton
    @Provides
    fun provideAppExecutor() = AppExecutor()

    @Provides
    fun provideResources(application: Application): Resources = application.resources




}