package com.example.myapplication.ui.base

class BaseContract {
    interface Presenter<in T> {
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View
}