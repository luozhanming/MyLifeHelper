package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.fragment.UserObject
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.github.vo.UserLogin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    val mLoginUser = MutableLiveData<UserObject?>()

    private val disposables = CompositeDisposable()

    fun loadLoginViewer() {
        disposables.add(userRepository.getLoginViewer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: UserObject? ->
                mLoginUser.value = t
                UserLogin.setUsername(t?.login())
            }, Throwable::printStackTrace)
        )
    }


}