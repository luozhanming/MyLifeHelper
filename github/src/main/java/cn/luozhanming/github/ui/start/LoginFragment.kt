package cn.luozhanming.github.ui.start

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
import cn.luozhanming.library.ext.getBaseActivity
import com.blankj.utilcode.util.BarUtils

class LoginFragment : BaseFragment<FragmentGithubLoginBinding>() {
    companion object {
        const val REQUEST_CODE_OAUTH = 1001
        const val EXTRA_CODE = "code"
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
                mViewModel.username.value,
                mViewModel.password.value
            )
            intent.putExtra(OAuthWebActivity.EXTRA_URL, url)
            startActivityForResult(intent, REQUEST_CODE_OAUTH)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.apply {
            BarUtils.setStatusBarColor(
                this,
                resources.getColor(R.color.colorPrimary)
            )
        }
        initObserver()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_OAUTH && resultCode == Activity.RESULT_OK) {
            mViewModel.login(data?.getStringExtra(EXTRA_CODE))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initObserver() {
        mViewModel.loginState.observe(this, Observer {
            when (it) {
                LoginViewModel.LOGIN_SUCCESS -> {
                    Toast.makeText(
                        activity,
                        "${resources.getString(R.string.login_success)}${it}",
                        Toast.LENGTH_SHORT
                    ).show()
                    getBaseActivity()?.hideLoadingDialog()
                }
                LoginViewModel.LOGIN_LOADING -> {
                    getBaseActivity()?.showLoadingDialog(resources.getString(R.string.login_loading))
                }
                LoginViewModel.LOGIN_FAILED -> {
                    Toast.makeText(
                        activity,
                        "${resources.getString(R.string.login_failed)}${it}",
                        Toast.LENGTH_SHORT
                    ).show()
                    getBaseActivity()?.hideLoadingDialog()
                }
            }
        })
    }
}