package com.josuearevalodev.setlistfmpro.screens.searchartists

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josuearevalodev.setlistfmpro.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchArtistsActivityTests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchArtistsActivity::class.java)

    @Test
    fun checkInitialTextIsBlank() {
        onView(withId(R.id.tietSearch))
            .check(matches(withText("")))
    }
}
