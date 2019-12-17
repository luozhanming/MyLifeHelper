package cn.luozhanming.github.di

import cn.luozhanming.github.ui.main.FeedFragment
import cn.luozhanming.github.ui.start.LoginFragment
import cn.luozhanming.github.ui.start.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class StartFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment():LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeWelcomeFragment():WelcomeFragment

}

@Module
abstract class MainFragmentModele(){
    @ContributesAndroidInjector
    abstract fun contributeMyActivityFragment():FeedFragment

}