package cn.luozhanming.github.di

import cn.luozhanming.github.ui.GithubMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GithubActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesGithubMainActivity(): GithubMainActivity

}