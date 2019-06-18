package com.example.myapplication.ui.preview

import com.example.myapplication.model.User
import com.example.myapplication.ui.base.BaseContract

class PreviewContract {
    interface View: BaseContract.View {
        fun showLoading()
        fun hideLoading()
        fun onLoadDataSuccess(user: User?)
        fun onLoadDataFailed(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}