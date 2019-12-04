package cn.luozhanming.github.ui.start

import android.os.Bundle
import android.os.Handler
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentGithubWelcomeBinding
import cn.luozhanming.github.common.GithubPreference
import cn.luozhanming.github.vo.UserLogin
import cn.luozhanming.library.common.AppConfig
import cn.luozhanming.library.di.Injectable
import cn.luozhanming.library.ext.setFullScreen

class WelcomeFragment : BaseFragment<FragmentGithubWelcomeBinding>(), Injectable {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //已有token就去
        activity?.setFullScreen(true)
        Handler().postDelayed({
            if (UserLogin.isLogin() && GithubPreference.getElement(
                    AppConfig.PREF_AUTO_LOGIN,
                    false
                )) {
                view?.apply {
                    navigationPopUpTo(
                        this , null,
                        R.id.action_welcomeFragment_to_mainActivity, true)
                }
            } else {
                view?.apply {
                    navigationPopUpTo(
                        this, null,
                        R.id.toLoginFragment, false
                    )
                }
            }
        }, 3000)

    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.setFullScreen(false)
    }

    override fun getLayoutId(): Int = R.layout.fragment_github_welcome

    override fun initViewModel() {

    }

    override fun initObserver() {
    }

    override fun initView() {
    }
}