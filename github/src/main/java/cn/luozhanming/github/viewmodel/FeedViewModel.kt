package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.luozhanming.github.repository.UserRepository
import cn.luozhanming.github.vo.Event
import cn.luozhanming.github.vo.Pager
import cn.luozhanming.github.vo.getEmptyPage
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val mPagerData by lazy {
        val livedata = MutableLiveData<Pager<Event>>()
        livedata.value = getEmptyPage(Event::class.java)
        livedata
    }


    fun loadFeeds() {
//        userRepository.getDynamicInfo(mCurPage.value!! + 1)
//            .subscribeOn(Schedulers.io())
//            .subscribe(Consumer {
//                if (it.isEmpty()) {
//                    mCurPage.postValue(mCurPage.value!! + 1)
//                    mCurPageDatas.postValue(it)
//                } else {
//                    mCurPageDatas.postValue(it)
//                }
//            }, object : RxCommonThrowable() {
//                override fun accept(t: Throwable) {
//                    super.accept(t)
//
//                }
//            })
    }
}