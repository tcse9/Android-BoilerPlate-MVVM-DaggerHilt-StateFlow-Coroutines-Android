package com.taufiq.hiltdemo.network

import com.taufiq.hiltdemo.model.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService){

    suspend fun getPost():List<Post> = apiService.getPost()
}