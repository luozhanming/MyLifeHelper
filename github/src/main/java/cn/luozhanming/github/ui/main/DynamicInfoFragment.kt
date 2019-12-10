package cn.luozhanming.github.ui.main

import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentMyActivityBinding
import cn.luozhanming.github.viewmodel.MyActivityViewModel
import cn.luozhanming.library.common.autoCleared

class DynamicInfoFragment : BaseFragment<FragmentMyActivityBinding>() {

    private var mViewModel: MyActivityViewModel by autoCleared()


    override fun getLayoutId(): Int = R.layout.fragment_my_activity

    override fun initViewModel() {
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MyActivityViewModel::class.java)
        mViewModel.loadNotifications()
    }

    override fun initObserver() {

    }

    override fun initView() {
    }
}