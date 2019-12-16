package cn.luozhanming.github.vo

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    val type: String,
    @SerializedName("display_login")
    val displayLogin: String
)
