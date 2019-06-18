package com.example.myapplication.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.di.component.DaggerActivityComponent
import com.example.myapplication.di.module.ActivityModule
import com.example.myapplication.ui.dialog.LoadingDialogFragment
import com.example.myapplication.ui.preview.PreviewFragment
import com.example.myapplication.utils.Constants.DIALOG_TAG
import com.example.myapplication.utils.Utils
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    MainContract.View,
    PreviewFragment.PreviewFragmentHandler {

    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var loadingDialogFragment: LoadingDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        loadingDialogFragment = LoadingDialogFragment.newInstance()
        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    override fun showLoading() {
        val ft = supportFragmentManager.beginTransaction()
        loadingDialogFragment.show(ft,DIALOG_TAG)
    }

    override fun hideLoading() {
        loadingDialogFragment.dismiss()
    }

    override fun showErrorMessage(error: String) {
        Utils.showErrorDialog(this, error)
    }
}
