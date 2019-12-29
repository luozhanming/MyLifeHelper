package cn.luozhanming.github.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cn.luozhanming.github.R
import cn.luozhanming.github.databinding.ItemReceiveEventBinding
import cn.luozhanming.github.vo.Event

class FeedAdapter(private val mDatas: MutableList<Event>) :
    RecyclerView.Adapter<GenericViewHolder<ItemReceiveEventBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder<ItemReceiveEventBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemReceiveEventBinding>(
            inflater,
            R.layout.item_receive_event,
            parent,
            false
        )
        return GenericViewHolder((binding))
    }

    override fun getItemCount(): Int = mDatas.size


    fun refreshDatas(datas:List<Event>){
        mDatas.clear()
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    fun addDatas(datas: List<Event>){
        val position = mDatas.size
        mDatas.addAll(datas)
        notifyItemRangeInserted(position,datas.size)
    }

    override fun onBindViewHolder(
        holder: GenericViewHolder<ItemReceiveEventBinding>,
        position: Int
    ) {
        val data = mDatas[position]
        holder.mBinding.event = data
        holder.mBinding.executePendingBindings()
    }
}