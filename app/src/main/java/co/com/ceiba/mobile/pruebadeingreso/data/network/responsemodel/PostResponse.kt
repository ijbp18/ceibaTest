package co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("userId")
    @Expose
    val userId: Int,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("body")
    @Expose
    val body: String
)