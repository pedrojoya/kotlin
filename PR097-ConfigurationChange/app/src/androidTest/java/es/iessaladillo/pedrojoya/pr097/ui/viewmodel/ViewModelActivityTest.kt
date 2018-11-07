package es.iessaladillo.pedrojoya.pr097.ui.viewmodel

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.extensions.rotateScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ViewModelActivityTest {

    @get:Rule
    val testRule = IntentsTestRule(ViewModelActivity::class.java)

    @Test
    fun shouldStartMethodStartThisActivity() {
        ViewModelActivity.start(testRule.activity)
        intended(hasComponent(ViewModelActivity::class.java.name))
    }

    // Initial state.

    @Test
    fun shouldShowScore0Initially() {
        assertDisplayed(R.id.lblScore, "0")
    }

    // Increment

    @Test
    fun shouldIncrementScoreWhenIncrementButtonClicked() {
        clickOn(R.id.btnIncrement)
        assertDisplayed(R.id.lblScore, "1")
    }

    // Rotation

    @Test
    fun shouldPreserveScoreAfterRotation() {
        clickOn(R.id.btnIncrement)
        assertDisplayed(R.id.lblScore, "1")
        testRule.activity.rotateScreen()
        assertDisplayed(R.id.lblScore, "1")
    }

}