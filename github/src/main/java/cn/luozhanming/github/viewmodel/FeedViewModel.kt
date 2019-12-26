package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.github.vo.Event
import cn.luozhanming.github.vo.PAGE_STATE_FAILED
import cn.luozhanming.github.vo.Pager
import cn.luozhanming.github.vo.getEmptyPage
import cn.luozhanming.library.common.RxCommonThrowable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val mPagerData by lazy {
        val livedata = MutableLiveData<Pager<Event>>()
        livedata.value = getEmptyPage(Event::class.java)
        livedata
    }


    fun loadFeeds(isLoadMore: Boolean) {
        userRepository.getDynamicInfo(if (isLoadMore) mPagerData.value!!.curPage + 1 else 1)
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                mPagerData.postValue(it)
            }, object : RxCommonThrowable() {
                override fun accept(t: Throwable) {
                    super.accept(t)
                    val pager = Pager<Event>(
                        mPagerData.value!!.curPage,
                        PAGE_STATE_FAILED,
                        mPagerData.value!!.datas
                    )
                    mPagerData.postValue(pager)
                }
            })
    }
}