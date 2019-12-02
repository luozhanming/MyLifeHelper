package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.LoginUserQuery
import cn.luozhanming.github.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

     val mLoginViewer = MutableLiveData<LoginUserQuery.Viewer>()

    fun loadLoginViewer(){
        userRepository.getLoginViewer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t ->
                mLoginViewer.postValue(t)
            },{ t ->
                t.printStackTrace()
            })
    }



}