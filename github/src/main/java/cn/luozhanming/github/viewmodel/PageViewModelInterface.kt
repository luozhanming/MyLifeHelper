package cn.luozhanming.github.viewmodel

import androidx.lifecycle.MutableLiveData

interface PageViewModelInterface<T> {
    companion object{
        const val STATE_HASDATA = 1001
        const val STATE_NOMOREDATA = 1002
        const val STATE_ERROR = 1003
    }

    val mCurPage: MutableLiveData<Int>
    val mCurPageDatas: MutableLiveData<List<T>>
    val state:MutableLiveData<Int>
}