package cn.luozhanming.github.ui.start

import android.os.Bundle
import android.os.Handler
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentGithubWelcomeBinding
import cn.luozhanming.github.preference.GithubPreference
import cn.luozhanming.github.vo.UserLogin
import cn.luozhanming.library.common.AppConfig
import cn.luozhanming.library.di.Injectable

class WelcomeFragment : BaseFragment<FragmentGithubWelcomeBinding>(), Injectable {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //已有token就去
        Handler().postDelayed({
            if (UserLogin.isLogin() && GithubPreference.getElement(
                    AppConfig.PREF_AUTO_LOGIN,
                    false
                )) {
               navigationPopUpTo(view?:throw IllegalStateException("Navigation view nulll"),
                   null,R.id.action_welcomeFragment_to_mainActivity,true)
            } else {
                navigationPopUpTo(
                    view ?: throw IllegalStateException("Navigation view nulll"),
                    null, R.id.toLoginFragment, false
                )
            }
        }, 3000)

    }

    override fun getLayoutId(): Int = R.layout.fragment_github_welcome

    override fun initViewModel() {

    }
}