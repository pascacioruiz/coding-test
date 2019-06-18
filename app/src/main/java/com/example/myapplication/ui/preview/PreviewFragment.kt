package com.example.myapplication.ui.preview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R
import com.example.myapplication.di.component.DaggerFragmentComponent
import com.example.myapplication.di.module.FragmentModule
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.fragment_preview.*
import javax.inject.Inject

class PreviewFragment : Fragment(), PreviewContract.View {
    @Inject
    lateinit var presenter: PreviewContract.Presenter
    private lateinit var rootView: View
    private lateinit var delegate: PreviewFragmentHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            delegate = context as PreviewFragmentHandler
        } catch (e: ClassCastException) {
            throw ClassCastException(e.printStackTrace().toString())
        }
    }

    interface PreviewFragmentHandler {
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage(error: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_preview, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val component = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        component.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    override fun showLoading() {
        delegate.showLoading()
    }

    override fun hideLoading() {
        delegate.hideLoading()
    }

    override fun onLoadDataSuccess(user: User?) {
        user?.let {
            name_text.text = getString(R.string.preview_fragment_name_holder, it.name, it.lastname)
            location_text.text = it.location
            mail_text.text = it.email
            link_text.text = it.website
            phone_text.text = it.phone
            position_text.text = it.currentJob?.position
            current_job_text.text = it.currentJob?.company
            current_job_desc_text.text = it.currentJob?.position
            current_job_title_text.text =
                getString(R.string.preview_fragment_experience_holder, it.currentJob?.company, it.currentJob?.since)
            past_job_desc_text.text = it.pastJob?.position
            past_job_title_text.text =
                getString(R.string.preview_fragment_experience_holder, it.pastJob?.company, it.pastJob?.since)
        }
    }

    override fun onLoadDataFailed(error: String) {
        delegate.showErrorMessage(error)
    }

}
