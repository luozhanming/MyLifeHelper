package cn.luozhanming.github.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.luozhanming.github.viewmodel.GithubViewModelFactory
import cn.luozhanming.github.viewmodel.LoginViewModel
import cn.luozhanming.github.viewmodel.MainViewModel
import cn.luozhanming.github.viewmodel.TestViewModel
import cn.luozhanming.library.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GithubViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}