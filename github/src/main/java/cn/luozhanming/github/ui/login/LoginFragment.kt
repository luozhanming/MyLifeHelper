package cn.luozhanming.github.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.luozhanming.github.R
import cn.luozhanming.github.base.BaseFragment
import cn.luozhanming.github.databinding.FragmentGithubLoginBinding
import cn.luozhanming.github.repository.LoginRepository
import cn.luozhanming.github.viewmodel.LoginViewModel
import cn.luozhanming.library.common.autoCleared

class LoginFragment : BaseFragment<FragmentGithubLoginBinding>() {
    companion object {
        const val REQUEST_CODE_OAUTH = 1001
    }

    private var mViewModel: LoginViewModel by autoCleared()

    override fun getLayoutId(): Int = R.layout.fragment_github_login


    override fun initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewmodel = mViewModel
        mBinding.btnLogin.setOnClickListener {
            val intent = Intent(activity, OAuthWebActivity::class.java)
            val url = LoginRepository.generateLoginOAuthUrl(
                mBinding.etUsername.text.toString(),
                mBinding.etPassword.text.toString()
            )
            intent.putExtra(OAuthWebActivity.EXTRA_URL, url)
            startActivityForResult(intent, REQUEST_CODE_OAUTH)
        }
    }

    override fun onResume() {
        super.onResume()
        initObserver()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== REQUEST_CODE_OAUTH&&resultCode==Activity.RESULT_OK){
            mViewModel.login(data?.getStringExtra("code"))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initObserver() {
        mViewModel.loginState.observe(this, Observer {
            if(it==LoginViewModel.LOGIN_SUCCESS){  //登录成功
                Toast.makeText(activity,"登录成功${it}",Toast.LENGTH_SHORT).show()
            }else{   //登录失败
                Toast.makeText(activity,"登录失败${it}",Toast.LENGTH_SHORT).show()
            }
        })
    }
}