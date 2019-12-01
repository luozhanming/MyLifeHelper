package cn.luozhanming.github.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.common.GithubPreference
import cn.luozhanming.github.repository.LoginRepository
import cn.luozhanming.github.vo.UserLogin
import cn.luozhanming.library.common.AppConfig
import cn.luozhanming.library.common.RxCommonThrowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class LoginViewModel @Inject constructor(val loginRepository: LoginRepository) : ViewModel() {
    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val loginState = MutableLiveData<Int>()

    val autoLogin = MutableLiveData<Boolean>()

    val mDisposables by lazy {
        CompositeDisposable()
    }

    companion object {
        const val LOGIN_FAILED = 1001
        const val LOGIN_SUCCESS = 1002
        const val LOGIN_LOADING = 1003
    }

    fun login(code: String?) {
        loginState.value = LOGIN_LOADING
        mDisposables.add(
            loginRepository.getAccessToken(code ?: "")
                .subscribe(
                    Consumer {
                        if (!TextUtils.isEmpty(it.token)) {
                            loginState.postValue(LOGIN_SUCCESS)
                            UserLogin.login(it.token ?: "")
                            GithubPreference.putElement(AppConfig.PREF_AUTO_LOGIN, autoLogin.value)
                            if (autoLogin.value ?: false) {
                                GithubPreference.putElement(AppConfig.PREF_TOKEN, it.token)
                            }
                        } else {
                            loginState.postValue(LOGIN_FAILED)
                        }
                    },
                    object : RxCommonThrowable() {
                        override fun accept(t: Throwable) {
                            super.accept(t)
                            loginState.postValue(LOGIN_FAILED)
                        }
                    })
        )
    }

}