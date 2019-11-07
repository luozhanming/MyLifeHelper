package cn.luozhanming.github.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cn.luozhanming.github.di.DaggerGithubComponent
import cn.luozhanming.library.LifeHelperApp
import cn.luozhanming.library.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class GithubMainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    //根Activity需要这个
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        DaggerGithubComponent.builder()
            .commonComponent((application as LifeHelperApp).getCommonComponent()).build()
            .inject(this)
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(
                fm: FragmentManager,
                f: Fragment,
                savedInstanceState: Bundle?
            ) {
                if (f is Injectable) {
                    AndroidSupportInjection.inject(f)
                }
            }
        }, true)
    }
}