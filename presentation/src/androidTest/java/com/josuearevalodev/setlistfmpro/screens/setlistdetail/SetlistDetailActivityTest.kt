package com.josuearevalodev.setlistfmpro.screens.setlistdetail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.josuearevalodev.setlistfmpro.R
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SetlistDetailActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<SetlistDetailActivity> = object : ActivityTestRule<SetlistDetailActivity>(SetlistDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext =
                    InstrumentationRegistry.getInstrumentation()
                        .targetContext
                val result = Intent(targetContext, SetlistDetailActivity::class.java)
                result.putExtra("EXTRA_ARTIST_ID", "c4707a18-2236-4426-9e67-429ce023777c")
                result.putExtra("EXTRA_SETLIST_ID", "39a9153")
                return result
            }
        }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }


    @Test
    fun testHeaderIsVisible() {
        onView(withId(R.id.clHeader)).check(matches(isDisplayed()))
    }
}
