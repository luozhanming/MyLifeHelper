package cn.luozhanming.github.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentGithubLoginBinding
import cn.luozhanming.github.viewmodel.LoginViewModel
import cn.luozhanming.library.common.autoCleared

class LoginFragment : BaseFragment<FragmentGithubLoginBinding>() {


    private var mViewModel: LoginViewModel by autoCleared()

    override fun getLayoutId(): Int = R.layout.fragment_github_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }
}