package com.example.myapplication

import com.example.myapplication.api.ApiServiceInterface
import com.example.myapplication.model.JobResponse
import com.example.myapplication.model.UserResponse
import com.example.myapplication.ui.preview.PreviewContract
import com.example.myapplication.ui.preview.PreviewPresenter
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * App's unit testing
 */
@RunWith(MockitoJUnitRunner::class)
class PreviewPresenterUnitTest {
    @Mock
    lateinit var apiService: ApiServiceInterface
    @Mock
    private lateinit var view: PreviewContract.View
    @Mock
    private lateinit var presenter: PreviewContract.Presenter
    private val currentJobResponse: JobResponse = JobResponse(
        company = "Pluralsight",
        position = "Web developer",
        since = "(2015 - current)",
        current = true
    )
    private val pastJobResponse: JobResponse = JobResponse(
        company = "Microsoft",
        position = "Software developer",
        since = "(2008 - 2015)",
        current = false
    )
    private val userResponse: UserResponse = UserResponse(
        name = "Deborah",
        lastname = "Kurata",
        location = "Pleasanton, CA",
        email = "kurata.deborah@pluralsight.com",
        phone = "+1 925-463-1955",
        website = "blogs.msmvps.com/deborahk",
        experience = listOf(currentJobResponse, pastJobResponse)
    )

    @Before
    fun setUp() {
        presenter = PreviewPresenter()
        presenter.attach(view)
    }

    @Test
    fun testIfLoadingIsShown() {
        presenter.loadData()
        verify(view).showLoading()
    }

    @Test
    fun testGetDataSuccess() {
        Mockito.`when`(apiService.getUser()).thenReturn(Single.just(userResponse))

        val call = apiService.getUser()
        val response = call.cache().blockingGet()

        Assert.assertEquals(response,userResponse)
    }
}
