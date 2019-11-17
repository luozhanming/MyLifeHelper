package cn.luozhanming.github.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
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
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnLogin.setOnClickListener {
            val intent = Intent(activity,OAuthWebActivity::class.java)
       
        }
    }
}