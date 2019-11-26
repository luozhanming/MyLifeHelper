package cn.luozhanming.github.ui.start

import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseGithubActivity
import cn.luozhanming.github.databinding.ActivityGithubStartBinding

class GithubStartActivity : BaseGithubActivity<ActivityGithubStartBinding>() {


    override fun getLayoutId(): Int = R.layout.activity_github_start

    override fun initViewModel() {
    }

    override fun initView() {
    }

    override fun daggerInject() {
        getGithubComponent().inject(this)
    }


}