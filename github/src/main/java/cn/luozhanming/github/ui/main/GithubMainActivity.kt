package cn.luozhanming.github.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseGithubActivity
import cn.luozhanming.github.databinding.ActivityGithubMainBinding
import cn.luozhanming.github.viewmodel.MainViewModel
import cn.luozhanming.library.common.autoCleared
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.activity_github_main.*
import kotlinx.android.synthetic.main.include_main_content.*


class GithubMainActivity : BaseGithubActivity<ActivityGithubMainBinding>() {


    private var mViewModel: MainViewModel by autoCleared()

    override fun initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        mBinding.mainViewModel = mViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_github_main

    override fun daggerInject() {
        getGithubComponent().inject(this)
    }

    override fun initView() {
        ll_drawer.setOnClickListener {
            if(!drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        initBnb()
    }

    private fun initBnb() {
        my_bnb.addItem(BottomNavigationItem(R.mipmap.ic_activity,getString(R.string.my_activity)))
            .addItem(BottomNavigationItem(R.mipmap.ic_repository,getString(R.string.my_repository)))
            .addItem(BottomNavigationItem(R.mipmap.ic_recommand,getString(R.string.my_recommand)))
            .initialise()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.loadLoginViewer()
    }
}