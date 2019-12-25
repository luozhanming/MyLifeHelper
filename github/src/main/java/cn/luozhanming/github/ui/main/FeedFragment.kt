package cn.luozhanming.github.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentFeedBinding
import cn.luozhanming.github.viewmodel.FeedViewModel
import cn.luozhanming.github.vo.PAGE_STATE_FAILED
import cn.luozhanming.github.vo.PAGE_STATE_NEVER
import cn.luozhanming.github.vo.PAGE_STATE_NO_MORE
import cn.luozhanming.github.vo.PAGE_STATE_SUCCESS
import cn.luozhanming.library.common.autoCleared

class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private var mViewModel: FeedViewModel by autoCleared()


    override fun getLayoutId(): Int = R.layout.fragment_feed

    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
        mViewModel.loadFeeds()
    }

    override fun initObserver() {
        mViewModel.mPagerData.observe(this, Observer {
            when (it.pageState) {
                PAGE_STATE_NEVER ->
                    mBinding.refreshLayout.autoRefresh()
                PAGE_STATE_SUCCESS -> {
                    if (it.curPage == 1) {  //刷新
                        mBinding.refreshLayout.finishRefresh()
                    } else {  //加载
                        mBinding.refreshLayout.finishLoadMore()
                    }
                    //更新adapter
                }
                PAGE_STATE_FAILED -> {
                    if (it.curPage == 1) {
                        mBinding.refreshLayout.finishRefresh(false)
                    } else {
                        mBinding.refreshLayout.finishLoadMore(false)
                    }
                }
                PAGE_STATE_NO_MORE -> {
                    if (it.curPage == 1) {
                        mBinding.refreshLayout.finishRefresh(false)
                        mBinding.refreshLayout.setNoMoreData(false)
                    } else {
                        mBinding.refreshLayout.finishLoadMore(false)
                        mBinding.refreshLayout.setNoMoreData(false)
                    }
                }
            }
        })

    }

    override fun initView() {
    }
}