package cn.luozhanming.github.ui.main

import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.LoginRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val loginRepository: LoginRepository):ViewModel() {
}