package com.taufiq.hiltdemo.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("body") val body:String
    )
