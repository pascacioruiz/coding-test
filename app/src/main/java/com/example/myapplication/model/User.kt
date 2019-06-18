package com.example.myapplication.model

data class User(
    var name: String = "",
    var lastname: String = "",
    var location: String = "",
    var email: String = "",
    var phone: String = "",
    var website: String = "",
    var currentJob: Job? = null,
    var pastJob: Job? = null
)
