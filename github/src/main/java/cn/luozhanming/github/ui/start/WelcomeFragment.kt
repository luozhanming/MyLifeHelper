package cn.luozhanming.github.ui.start

import android.os.Bundle
import android.os.Handler
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentGithubWelcomeBinding
import cn.luozhanming.library.di.Injectable

class WelcomeFragment : BaseFragment<FragmentGithubWelcomeBinding>(), Injectable {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            navigationPopUpTo(
                view ?: throw IllegalStateException("Navigation view nulll"),
                null, R.id.toLoginFragment, false
            )
        }, 3000)

    }

    override fun getLayoutId(): Int = R.layout.fragment_github_welcome

    override fun initViewModel() {

    }
}