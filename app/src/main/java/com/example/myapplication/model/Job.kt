package com.example.myapplication.model

data class Job(
    val company: String = "",
    val position: String = "",
    val since: String = "",
    val current: Boolean = false
)