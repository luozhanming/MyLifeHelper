package cn.luozhanming.github.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.luozhanming.github.R
import cn.luozhanming.github.databinding.ItemReceiveEventBinding
import cn.luozhanming.github.vo.Event

class FeedAdapter : PagedListAdapter<Event, FeedAdapter.ViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Event>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldEvent: Event,
                newEvent: Event
            ) = oldEvent.id == newEvent.id

            override fun areContentsTheSame(
                oldEvent: Event,
                newEvent: Event
            ) = oldEvent == newEvent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemReceiveEventBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_receive_event, parent, false
        )
        return ViewHolder(binding.root, binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.binding.event = event
        holder.binding.executePendingBindings()
    }


    class ViewHolder(itemView: View, val binding: ItemReceiveEventBinding) :
        RecyclerView.ViewHolder(itemView)

}