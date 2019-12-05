package cn.luozhanming.github.di

import cn.luozhanming.github.ui.main.GithubMainActivity
import cn.luozhanming.github.ui.start.GithubStartActivity
import cn.luozhanming.library.di.CommonComponent
import dagger.Component
import dagger.android.AndroidInjectionModule

@GithubScope
@Component(
    modules = [GithubModule::class, GithubViewModelModule::class, AndroidInjectionModule::class
        , StartFragmentModule::class,MainFragmentModele::class],
    dependencies = [CommonComponent::class]
)
interface GithubComponent {

    @Component.Builder
    interface Builder {
        //依赖组件需要引入该组件实例
        fun commonComponent(component: CommonComponent): Builder

        fun build(): GithubComponent
    }

    fun inject(activity: GithubStartActivity)

    fun inject(activity: GithubMainActivity)

}