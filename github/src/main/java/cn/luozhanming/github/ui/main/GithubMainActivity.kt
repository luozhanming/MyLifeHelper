package cn.luozhanming.github.ui.main

import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseGithubActivity
import cn.luozhanming.github.databinding.ActivityGithubMainBinding


class GithubMainActivity : BaseGithubActivity<ActivityGithubMainBinding>() {

    override fun initViewModel() {
    }

    override fun getLayoutId(): Int = R.layout.activity_github_main

    override fun daggerInject() {
        getGithubComponent().inject(this)
    }

    override fun initView() {
    }
}