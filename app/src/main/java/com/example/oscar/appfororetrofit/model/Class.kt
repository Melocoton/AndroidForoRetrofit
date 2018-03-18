package com.example.oscar.appfororetrofit.model

import com.example.oscar.appfororetrofit.api.ApiForoService

/**
 * Created by Oscar on 18/03/2018.
 */
data class Comment(var comment:String, var user_username:String)
data class Post(var idpost: String, var post:String, var user_username: String)
data class Result(var result: Boolean)

object ApiForoSingleton {
    var cliente: ApiForoService? = null
}