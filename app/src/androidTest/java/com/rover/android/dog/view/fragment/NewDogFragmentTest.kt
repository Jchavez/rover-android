package com.rover.android.dog.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rover.android.R
import com.rover.android.common.util.FragmentUtil.getResourceString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewDogFragmentTest {

    @Test
    fun launchFragment() {
        launchFragmentInContainer<NewDogFragment>()
        onView(withId(R.id.dogNameEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.dogNameEditText)).check(matches(withHint(getResourceString(R.string.edit_text_hint))))

        onView(withId(R.id.dogPhotoPreviewView)).check(matches(isDisplayed()))

        onView(withId(R.id.capturePhotoButton)).check(matches(isDisplayed()))
        onView(withId(R.id.capturePhotoButton)).check(matches(withText(getResourceString(R.string.capture_button))))

        onView(withId(R.id.saveButton)).check(matches(isDisplayed()))
        onView(withId(R.id.saveButton)).check(matches(withText(getResourceString(R.string.button_save))))
    }
}