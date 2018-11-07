package es.iessaladillo.pedrojoya.pr002

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import es.iessaladillo.pedrojoya.pr002.ui.main.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

    // Initial state

    @Test
    fun shouldCheckBoxBeCheckedInitially() {
        assertChecked(R.id.chkPolite)
        assertDisplayed(R.id.chkPolite, R.string.main_polite_mode)
    }

    // Checkbox

    @Test
    fun shouldCheckBoxShowImpoliteModeWhenUnChecked() {
        clickOn(R.id.chkPolite)
        assertDisplayed(R.id.chkPolite, R.string.main_impolite_mode)
    }

    @Test
    fun shouldCheckBoxShowPoliteModeWhenChecked() {
        clickOn(R.id.chkPolite)
        clickOn(R.id.chkPolite)
        assertDisplayed(R.id.chkPolite, R.string.main_polite_mode)
    }

    @Test
    fun shouldGreetPolitelyWhenCheckedAndButtonPressed() {
        val name = "Test"
        writeTo(R.id.txtName, name)
        clickOn(R.id.btnGreet)
        val message = (testRule.activity.getString(R.string.main_good_morning) +
                testRule.activity.getString(R.string.main_nice_to_meet_you) + " "
                + name)
        onView(withText(message))
                .inRoot(withDecorView(not(testRule.activity.window
                        .decorView))).check(matches(isDisplayed()))
    }

    @Test
    fun shouldGreetImPolitelyWhenUncheckedAndButtonPressed() {
        val name = "Test"
        clickOn(R.id.chkPolite)
        writeTo(R.id.txtName, name)
        clickOn(R.id.btnGreet)
        val message = (testRule.activity.getString(R.string.main_good_morning) + " "
                + name)
        onView(withText(message))
                .inRoot(withDecorView(not(testRule.activity.window
                        .decorView))).check(matches(isDisplayed()))
    }

}