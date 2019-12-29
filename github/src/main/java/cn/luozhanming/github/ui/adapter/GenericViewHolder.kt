package cn.luozhanming.github.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class GenericViewHolder<T:ViewDataBinding>(val mBinding: T) : RecyclerView.ViewHolder(mBinding.root)