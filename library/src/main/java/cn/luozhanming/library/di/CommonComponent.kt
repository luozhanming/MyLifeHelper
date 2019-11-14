package cn.luozhanming.library.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import cn.luozhanming.library.LifeHelperApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Named
import javax.inject.Singleton

/**
 * 全局通用Component
 * */
@Singleton
@Component(modules = [CommonModule::class])
interface CommonComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CommonComponent
    }

    fun inject(application:LifeHelperApp)

}