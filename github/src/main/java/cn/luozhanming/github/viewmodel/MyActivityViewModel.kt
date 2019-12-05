package cn.luozhanming.github.viewmodel

import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.library.common.RxCommonThrowable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyActivityViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {


    fun loadNotifications(){
        userRepository.getNotifacations()
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                val string = it.string()
            }, RxCommonThrowable())
    }
}