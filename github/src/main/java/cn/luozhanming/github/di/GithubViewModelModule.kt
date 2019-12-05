package cn.luozhanming.github.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.luozhanming.github.viewmodel.*
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
    @IntoMap
    @ViewModelKey(MyActivityViewModel::class)
    abstract fun bindMyActivityViewModel(viewModel: MyActivityViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}