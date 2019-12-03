package cn.luozhanming.github.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseGithubActivity
import cn.luozhanming.github.databinding.ActivityGithubMainBinding
import cn.luozhanming.github.viewmodel.MainViewModel
import cn.luozhanming.library.common.autoCleared


class GithubMainActivity : BaseGithubActivity<ActivityGithubMainBinding>() {


    private var mViewModel: MainViewModel by autoCleared()

    override fun initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_github_main

    override fun daggerInject() {
        getGithubComponent().inject(this)
    }

    override fun initView() {
        mBinding.mainViewModel = mViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.loadLoginViewer()
    }
}