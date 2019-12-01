package cn.luozhanming.github.viewmodel

import LoginUserQuery
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.UserRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

     val mLoginViewer = MutableLiveData<LoginUserQuery.Viewer>()

    fun loadLoginViewer(){
        userRepository.getLoginViewer()
            .subscribeOn(Schedulers.io())
            .subscribe({ t ->
                mLoginViewer.postValue(t)
            },{ t ->  })
    }



}