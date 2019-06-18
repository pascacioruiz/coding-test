package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("location") val location: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String,
    @SerializedName("experience") val experience: List<JobResponse>
)