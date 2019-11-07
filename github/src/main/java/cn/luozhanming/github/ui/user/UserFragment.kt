package cn.luozhanming.github.ui.user

import androidx.fragment.app.Fragment
import cn.luozhanming.library.di.Injectable
import javax.inject.Inject

class UserFragment : Fragment(), Injectable {

    @Inject
    lateinit var str:String
}