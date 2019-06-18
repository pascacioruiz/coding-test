package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("company") val company: String,
    @SerializedName("position") val position: String,
    @SerializedName("since") val since: String,
    @SerializedName("current") val current: Boolean
)