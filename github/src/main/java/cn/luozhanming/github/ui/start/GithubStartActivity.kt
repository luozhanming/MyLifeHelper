package cn.luozhanming.github.ui.start

import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseGithubActivity
import cn.luozhanming.github.databinding.ActivityGithubMainBinding

class GithubStartActivity : BaseGithubActivity<ActivityGithubMainBinding>()
{


    override fun getLayoutId(): Int = R.layout.activity_github_main

    override fun initViewModel() {
    }

    override fun initView() {
    }

    override fun daggerInject() {
        getGithubComponent().inject(this)
    }



}