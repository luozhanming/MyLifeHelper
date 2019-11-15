package cn.luozhanming.library.di

import android.app.Application
import cn.luozhanming.library.LifeHelperApp
import cn.luozhanming.library.common.AppExecutor
import dagger.BindsInstance
import dagger.Component
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

    fun inject(application: LifeHelperApp)

    /*由于是依赖的组件，所以提供的依赖必须提供接口返回*/
    fun appExecutors(): AppExecutor

}