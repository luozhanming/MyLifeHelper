package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.github.vo.Event
import cn.luozhanming.library.common.RxCommonThrowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyActivityViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {


    val receiveEvents: MutableLiveData<List<Event>> = MutableLiveData()

    fun loadNotifications() {
        userRepository.getDynamicInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                receiveEvents.postValue(it)
            }, RxCommonThrowable())
    }
}