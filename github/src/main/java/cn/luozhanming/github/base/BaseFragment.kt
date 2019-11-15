package cn.luozhanming.github.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import cn.luozhanming.github.viewmodel.GithubViewModelFactory
import cn.luozhanming.library.common.autoCleared
import cn.luozhanming.library.di.Injectable
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding> : Fragment(),Injectable {

    var mBinding: T by autoCleared()

    @Inject
    lateinit var viewModelFactory: GithubViewModelFactory

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化ViewModel,没有Injectable可以不用理
     * */
    abstract fun initViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }


}