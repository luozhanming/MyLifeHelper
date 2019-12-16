package cn.luozhanming.github.vo

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: String,
    @SerializedName("node_id") val nodeId: String,
    val name:String,
    @SerializedName("full_name") val fullName: String,
    val private: Boolean,
    @SerializedName("html_url") val htmlUrl: String,
    val description: String,
    val fork: Boolean,
    val language: String?,
    val url: String,
    @SerializedName("fork_url") val forkUrl: String,
    @SerializedName("stargazers_count") val starCount: Int,
    @SerializedName("watchers_count") val watchCount: Int
)