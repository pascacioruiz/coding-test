package com.example.myapplication.api

import com.example.myapplication.model.UserResponse
import com.example.myapplication.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("cv_user.json")
    fun getUser(): Single<UserResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}