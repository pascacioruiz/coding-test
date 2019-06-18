package com.example.myapplication

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

import com.example.myapplication.ui.main.MainActivity
import org.junit.Rule

/**
 * App's instrumented test
 */
@RunWith(AndroidJUnit4::class)
class PreviewFragmentInstrumentedTest {
    @Rule @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfTitlesAreDisplayed() {
        //TODO: fix issue with execution time
        onView(withId(R.id.experience_text)).check(matches(isDisplayed()))
        onView(withId(R.id.contact_text)).check(matches(isDisplayed()))
    }
}
