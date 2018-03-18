package com.example.oscar.appfororetrofit.api

/**
 * Created by Oscar on 18/03/2018.
 */

import com.example.oscar.appfororetrofit.model.Comment
import com.example.oscar.appfororetrofit.model.Post
import com.example.oscar.appfororetrofit.model.Result
import okhttp3.OkHttpClient
import okhttp3.JavaNetCookieJar
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import java.net.CookieHandler
import java.net.CookieManager
import java.util.concurrent.TimeUnit

interface ApiForoService {

    @POST("/apiforo/login")
    @FormUrlEncoded
    fun login(
            @Field("name") Username: String,
            @Field("password") Password: String
    ):Call<Result>

    @POST("/apiforo/register")
    @FormUrlEncoded
    fun register(
            @Field("name") Username: String,
            @Field("password") Password: String,
            @Field("email") Email: String
    ):Call<Result>

    @POST("/apiforo/create/post/{name}")
    fun createPost(
            @Path("name") Title: String
    ):Call<Result>

    @POST("/apiforo/create/comment")
    @FormUrlEncoded
    fun createComment(
            @Field("comment") Comment: String,
            @Field("post") Post: String
    ):Call<Result>

    @GET("/apiforo/list/post")
    fun listaPost(): Call<List<Post>>

    @GET("/apiforo/list/comment")
    fun listaComment(
            @Query("post") Post: String
    ): Call<List<Comment>>

    companion object {
        fun create(): ApiForoService{
            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            val cookieHandler: CookieHandler = CookieManager()
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                    .cookieJar(JavaNetCookieJar(cookieHandler))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://estomelohancambiado.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(ApiForoService::class.java)
        }

    }

}