package cn.luozhanming.github.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.databinding.ActivityGithubMainBinding
import cn.luozhanming.github.databinding.ActivityMainBinding
import cn.luozhanming.github.di.DaggerGithubComponent
import cn.luozhanming.github.viewmodel.GithubViewModelFactory
import cn.luozhanming.github.viewmodel.TestViewModel
import cn.luozhanming.library.LifeHelperApp
import cn.luozhanming.library.base.BaseActivity
import cn.luozhanming.library.common.AppExecutor
import cn.luozhanming.library.common.autoCleared
import cn.luozhanming.library.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class GithubMainActivity : BaseActivity(), HasSupportFragmentInjector {


    //根Activity需要这个
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: GithubViewModelFactory

    private var binding: ActivityGithubMainBinding by autoCleared()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github_main)
        //注入
        DaggerGithubComponent.builder()
            .commonComponent((application as LifeHelperApp).getCommonComponent()).build()
            .inject(this)
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                super.onFragmentAttached(fm, f, context)
                if (f is Injectable) {
                    AndroidSupportInjection.inject(f)
                }
            }

        }, true)

    }

    override fun initView() {

    }
}