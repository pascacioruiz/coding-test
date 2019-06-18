package com.example.myapplication.ui.preview

import com.example.myapplication.api.ApiServiceInterface
import com.example.myapplication.model.Job
import com.example.myapplication.model.JobResponse
import com.example.myapplication.model.User
import com.example.myapplication.model.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PreviewPresenter : PreviewContract.Presenter {
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private val subscriptions = CompositeDisposable()
    private lateinit var view: PreviewContract.View

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: PreviewContract.View) {
        this.view = view
    }

    override fun loadData() {
        view.showLoading()

        val subscribe = api.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userResponse: UserResponse? ->
                view.hideLoading()
                val usr = orderJobs(userResponse)
                view.onLoadDataSuccess(usr)
            }, { error ->
                view.hideLoading()
                view.onLoadDataFailed(error.localizedMessage)
            })
        subscriptions.add(subscribe)
    }

    private fun orderJobs(userResponse: UserResponse?): User {
        lateinit var current: JobResponse
        lateinit var past: JobResponse

        userResponse?.experience?.forEach {
            if (it.current) {
                current = it
            } else {
                past = it
            }
        }

        return User().apply {
            if (userResponse != null) {
                name = userResponse.name
                lastname = userResponse.lastname
                location = userResponse.location
                email = userResponse.email
                phone = userResponse.phone
                website = userResponse.website
                currentJob = Job(current.company, current.position, current.since, current.current)
                pastJob = Job(past.company,past.position,past.since,past.current)
            }
        }
    }
}