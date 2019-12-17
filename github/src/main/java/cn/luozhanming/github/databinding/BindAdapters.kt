package cn.luozhanming.github.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import cn.luozhanming.github.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["app:imageUrl", "app:circleCrop"], requireAll = false)
fun bindImageUrl(imageview: ImageView, url: String?, circleCrop: Boolean) {
    Glide.with(imageview).load(url)
        .placeholder(R.mipmap.ic_gichub_user)
        .apply(if (circleCrop) RequestOptions().circleCrop() else RequestOptions())
        .into(imageview)
}

