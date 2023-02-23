package com.rover.android.dog.view.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rover.android.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogItemDialogFragmentTest {

    private companion object {
        private const val DOG_NAME = "DOG_NAME"
        private const val DOG_URI = "DOG_URI"
    }

    @Test
    fun launchFragment() {
        val fragmentArgs = bundleOf("dogName" to DOG_NAME, "dogUri" to DOG_URI)
        launchFragmentInContainer<DogItemDialogFragment>(fragmentArgs)

        onView(withId(R.id.nameDogItemTextView)).check(matches(withText(DOG_NAME)))
        onView(withId(R.id.photoDogItemImageView)).check(matches(isDisplayed()))
    }
}