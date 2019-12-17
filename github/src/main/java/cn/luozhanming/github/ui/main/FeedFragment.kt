package cn.luozhanming.github.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentFeedBinding
import cn.luozhanming.github.viewmodel.FeedViewModel
import cn.luozhanming.github.viewmodel.PageViewModelInterface
import cn.luozhanming.library.common.autoCleared
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private var mViewModel: FeedViewModel by autoCleared()


    override fun getLayoutId(): Int = R.layout.fragment_feed

    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
        mViewModel.loadNotifications()
    }

    override fun initObserver() {
        mViewModel.mCurPageDatas.observe(this, Observer {
            if(it.isEmpty()){
           //     refresh_layout
            }
        })
        mViewModel.state.observe(this, Observer {
            when(it){
                PageViewModelInterface.STATE_HASDATA->{
                    refresh_layout.finishRefresh(true)
                }
                PageViewModelInterface.STATE_ERROR->{
                    refresh_layout.finishRefresh(false)
                }
                PageViewModelInterface.STATE_NOMOREDATA->{
                    if(mViewModel.mCurPage.value!!>0){
                        refresh_layout.finishLoadMoreWithNoMoreData()
                    }else{
                        refresh_layout.finishRefreshWithNoMoreData()
                    }
                }
            }
        })
    }

    override fun initView() {
    }
}