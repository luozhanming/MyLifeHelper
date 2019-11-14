package cn.luozhanming.library.di

import cn.luozhanming.library.common.AppExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CommonModule {

    @Singleton
    @Provides
    fun provideAppExecutor() = AppExecutor()
}