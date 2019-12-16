package cn.luozhanming.github.vo

import com.google.gson.annotations.SerializedName

data class Event(
    val id: String? = null,
    val type: String? = null,
    val actor: User? = null,
    val repo: Repository? = null,
    val org: User? = null,
    val payload: EventPayload? = null,
    @SerializedName("public")
    val isPublic: Boolean = false,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updateAt: String? = null
)
