package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josuearevalodev.setlistfmpro.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
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

    @Test
    fun checkNavigatesOnTapSearch() {

        // 1 - Set band text in EditText
        val textInputEditText = onView(withId(R.id.tietSearch))
        textInputEditText.perform(ViewActions.replaceText("UFO"), ViewActions.closeSoftKeyboard())
        textInputEditText.perform(ViewActions.pressImeActionButton())

        // 2 - Click on Search icon
        val checkableImageButton = onView(
            Matchers.allOf(
                withId(R.id.text_input_end_icon),
                isDisplayed()
            )
        )

        checkableImageButton.perform(ViewActions.click())

        // 3 - Check that ArtistSetlistsActivity's View Pager is displayed
        val viewPager = onView(withId(R.id.vpArtistSetlists))
        viewPager.check(matches(isDisplayed()))
    }
}
