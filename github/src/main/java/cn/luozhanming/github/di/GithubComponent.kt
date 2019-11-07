package cn.luozhanming.github.di

import android.app.Application
import cn.luozhanming.github.ui.GithubMainActivity
import cn.luozhanming.library.di.CommonComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@GithubScope
@Component(
    modules = [GithubModule::class, GithubViewModelModule::class, AndroidInjectionModule::class],
    dependencies = [CommonComponent::class]
)
interface GithubComponent {


    @Component.Builder
    interface Builder {
        //依赖组件需要引入该组件实例
        fun commonComponent(component: CommonComponent):Builder

        fun build(): GithubComponent
    }

    fun inject(activity: GithubMainActivity)

}