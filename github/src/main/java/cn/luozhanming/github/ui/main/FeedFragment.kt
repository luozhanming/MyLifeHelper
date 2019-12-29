package cn.luozhanming.github.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentFeedBinding
import cn.luozhanming.github.ui.adapter.FeedAdapter
import cn.luozhanming.github.viewmodel.FeedViewModel
import cn.luozhanming.github.vo.PAGE_STATE_FAILED
import cn.luozhanming.github.vo.PAGE_STATE_NEVER
import cn.luozhanming.github.vo.PAGE_STATE_NO_MORE
import cn.luozhanming.github.vo.PAGE_STATE_SUCCESS
import cn.luozhanming.library.common.autoCleared
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener

class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private var mViewModel: FeedViewModel by autoCleared()
    private var mFeedAdapter: FeedAdapter by autoCleared()


    override fun getLayoutId(): Int = R.layout.fragment_feed

    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
    }

    override fun initObserver() {
        mViewModel.mPagerData.observe(this, Observer {
            when (it.pageState) {
                PAGE_STATE_NEVER ->
                    mBinding.refreshLayout.autoRefresh()
                PAGE_STATE_SUCCESS -> {
                    if (it.curPage == 1) {  //刷新
                        mBinding.refreshLayout.finishRefresh()
                        mFeedAdapter.refreshDatas(it.datas!!)
                    } else {  //加载
                        mBinding.refreshLayout.finishLoadMore()
                        mFeedAdapter.addDatas(it.datas!!)
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
                        mBinding.refreshLayout.finishRefresh()
                        mBinding.refreshLayout.setNoMoreData(true)
                    } else {
                        mBinding.refreshLayout.finishLoadMore()
                        mBinding.refreshLayout.setNoMoreData(true)
                        mBinding.refreshLayout.finishLoadMoreWithNoMoreData()
                    }
                }
            }
        })

    }

    override fun initView() {
        mBinding.rvEventList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mFeedAdapter = FeedAdapter(arrayListOf())
        mBinding.rvEventList.adapter = mFeedAdapter
        mBinding.refreshLayout.setOnMultiPurposeListener(object :SimpleMultiPurposeListener(){
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mViewModel.loadFeeds(true)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mBinding.refreshLayout.setNoMoreData(false)
                mViewModel.loadFeeds(false)
            }
        })
    }
}