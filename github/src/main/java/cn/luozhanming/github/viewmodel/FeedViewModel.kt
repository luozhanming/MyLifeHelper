package cn.luozhanming.github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.github.vo.Event
import cn.luozhanming.library.common.RxCommonThrowable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val userRepository: UserRepository) :
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
    override val state: MutableLiveData<Int> by lazy {
        MutableLiveData()
    }

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
            }, object : RxCommonThrowable() {
                override fun accept(t: Throwable) {
                    super.accept(t)

                }
            })
    }
}