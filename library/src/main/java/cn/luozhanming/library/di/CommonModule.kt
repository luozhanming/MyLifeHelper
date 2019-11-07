package cn.luozhanming.library.di

import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class CommonModule {

    @Named("dsf")
    @Provides
    fun proviceString():String = "sdfsdf"
}