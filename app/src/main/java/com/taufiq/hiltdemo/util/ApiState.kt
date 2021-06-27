package com.taufiq.hiltdemo.util

import com.taufiq.hiltdemo.model.Post

sealed class ApiState{

    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Post>) : ApiState()
    object Empty : ApiState()
}
