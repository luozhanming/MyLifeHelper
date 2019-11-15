package cn.luozhanming.github.di

import cn.luozhanming.github.ui.GithubMainActivity
import cn.luozhanming.github.ui.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GithubActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeGithubMainActivity(): GithubMainActivity

}