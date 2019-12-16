package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.github.vo.Event
import cn.luozhanming.library.common.RxCommonThrowable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyActivityViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel(), PageViewModelInterface<Event> {
    override val mCurPage: MutableLiveData<Int> by lazy {
        val data = MutableLiveData<Int>()
        data.value = 0
        data
    }
    override val mCurPageDatas: MutableLiveData<List<Event>> by lazy {
        val data = MutableLiveData<List<Event>>()
        data

    }
    override val state: MutableLiveData<Int>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    val receiveEvents: MutableLiveData<List<Event>> = MutableLiveData()

    fun loadNotifications() {
        userRepository.getDynamicInfo(mCurPage.value!! + 1)
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                if (it.isEmpty()) {
                    mCurPage.postValue(mCurPage.value!! + 1)
                    mCurPageDatas.postValue(it)
                } else {
                    mCurPageDatas.postValue(it)
                }
            }, object :RxCommonThrowable(){
                override fun accept(t: Throwable) {
                    super.accept(t)

                }
            })
    }
}