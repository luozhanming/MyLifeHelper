package cn.luozhanming.github.di

import cn.luozhanming.github.ui.login.LoginFragment
import cn.luozhanming.github.ui.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment():LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment():UserFragment
}