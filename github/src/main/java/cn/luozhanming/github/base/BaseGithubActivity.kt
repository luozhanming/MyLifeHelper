package cn.luozhanming.github.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cn.luozhanming.github.di.DaggerGithubComponent
import cn.luozhanming.github.viewmodel.GithubViewModelFactory
import cn.luozhanming.library.LifeHelperApp
import cn.luozhanming.library.base.BaseActivity
import cn.luozhanming.library.common.autoCleared
import cn.luozhanming.library.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseGithubActivity<T : ViewDataBinding> : BaseActivity(),
    HasSupportFragmentInjector {

    //根Activity需要这个
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var mBinding: T by autoCleared()

    @Inject
    lateinit var viewModelFactory: GithubViewModelFactory


    /**
     * 初始化ViewModel
     * */
    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        daggerInject()
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                super.onFragmentAttached(fm, f, context)
                if(f is Injectable){
                    AndroidSupportInjection.inject(f)
                }
            }
        },true)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    /**
     * 获取GithubComponent，需要到GithubComponent注册injectActivity
     * */
    fun getGithubComponent() = DaggerGithubComponent.builder()
        .commonComponent((application as LifeHelperApp).getCommonComponent()).build()


    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 注入依赖
     * */
    abstract fun daggerInject()





}
