package com.devindie.hieuvetraitim.entities

import com.google.gson.annotations.SerializedName


data class Feeling(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("raw")
    val raw: String? = null,
    @SerializedName("subtitle")
    val subtitle: ArrayList<Feeling> = arrayListOf()
)
