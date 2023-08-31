package com.rome.tech.ticmasproject

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rome.tech.ticmasproject.compare.mock.MockTestData
import com.rome.tech.ticmasproject.compare.view.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 * Test with Android context data from the emulator
 */

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    // Rule provides the scenario linked to the MainActivity
    @get: Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivity_clickOn_compareWithEqualData() {
        val caseList: List<List<String>> = MockTestData().equalsData()

        Espresso.onView(ViewMatchers.withId(R.id.str1)).perform(
            ViewActions.click(), typeText(caseList[0][0])
        );

        Espresso.onView(ViewMatchers.withId(R.id.str2)).perform(
            ViewActions.click(), typeText(caseList[0][1])
        );

        Espresso.onView(ViewMatchers.withId(R.id.actionCompare)).perform(ViewActions.click())

        Espresso.onView(
            ViewMatchers.withId(R.id.result)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.okResult)
            )
        )

    }

    @Test
    fun mainActivity_clickOn_compareWithDifferentData() {
        val caseList: List<List<String>> = MockTestData().diferentData()

        Espresso.onView(ViewMatchers.withId(R.id.str1)).perform(
            ViewActions.click(), typeText(caseList[0][0])
        );

        Espresso.onView(ViewMatchers.withId(R.id.str2)).perform(
            ViewActions.click(), typeText(caseList[0][1])
        );

        Espresso.onView(ViewMatchers.withId(R.id.actionCompare)).perform(ViewActions.click())

        Espresso.onView(
            ViewMatchers.withId(R.id.result)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.notOkResult)
            )
        )
    }

    @Test
    fun mainActivity_clickOn_compareWithoutData() {
        Espresso.onView(ViewMatchers.withId(R.id.actionCompare)).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.result)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.resetResult)
            )
        )
    }

    @Test
    fun mainActivity_clickOn_compareWithoutSomeData() {
        Espresso.onView(ViewMatchers.withId(R.id.str2)).perform(
            ViewActions.click(), typeText("Hola")
        );

        Espresso.onView(ViewMatchers.withId(R.id.actionCompare)).perform(ViewActions.click())

        Espresso.onView(
            ViewMatchers.withId(R.id.result)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.resetResult)
            )
        )
    }

//    @Test
//    fun mainActivity_clickOn_resetWithData() {
//        // Check only the content of EditTexts
//        val caseList: List<List<String>> = MockTestData().equalsData()
//        Espresso.onView(ViewMatchers.withId(R.id.str1)).perform(
//            ViewActions.click(), typeText(caseList[0][0])
//        );
//
//        Espresso.onView(ViewMatchers.withId(R.id.str2)).perform(
//            ViewActions.click(), typeText(caseList[0][1])
//        );
//
//        Espresso.onView(ViewMatchers.withId(R.id.actionReset)).perform(
//            ViewActions.click()
//        )
//
//        Espresso.onView(
//            ViewMatchers.withId(R.id.str1)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.withText("")
//            )
//        )
//
//        Espresso.onView(
//            ViewMatchers.withId(R.id.str2)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.withText("")
//            )
//        )
//    }

//    @Test
//    fun mainActivity_clickOn_resetWithoutData() {
//        Espresso.onView(ViewMatchers.withId(R.id.actionReset)).perform(
//            ViewActions.click()
//        )
//
//        Espresso.onView(
//            ViewMatchers.withId(R.id.result)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.withText(R.string.resetResult)
//            )
//        )
//    }

}