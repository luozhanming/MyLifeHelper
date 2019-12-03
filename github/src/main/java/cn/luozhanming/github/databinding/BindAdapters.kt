package cn.luozhanming.github.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageUrl(imageview: ImageView, url: String) {
    Glide.with(imageview).load(url).into(imageview)
}