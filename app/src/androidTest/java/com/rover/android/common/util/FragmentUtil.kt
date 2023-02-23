package com.rover.android.common.util

import androidx.test.platform.app.InstrumentationRegistry

object FragmentUtil {

    fun getResourceString(id: Int): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(id)
    }
}