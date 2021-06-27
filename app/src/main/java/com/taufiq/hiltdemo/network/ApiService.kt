package com.taufiq.hiltdemo.network

import com.taufiq.hiltdemo.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost():List<Post>
}