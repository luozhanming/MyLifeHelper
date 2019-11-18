package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.LoginRepository
import cn.luozhanming.github.vo.AccessToken
import javax.inject.Inject

class LoginViewModel @Inject constructor(val loginRepository: LoginRepository) : ViewModel() {
    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()


    fun login(code: String?) {
        loginRepository.getAccessToken(code?:"")
            .subscribe({ t: AccessToken? ->
                t.toString()
            }, { t ->
                t.printStackTrace()
            })
    }

}