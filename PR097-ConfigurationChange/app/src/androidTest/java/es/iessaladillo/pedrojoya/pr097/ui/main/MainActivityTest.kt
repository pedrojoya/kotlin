package es.iessaladillo.pedrojoya.pr097.ui.main

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.ui.retain.RetainActivity
import es.iessaladillo.pedrojoya.pr097.ui.save.SaveActivity
import es.iessaladillo.pedrojoya.pr097.ui.state.AndroidStateActivity
import es.iessaladillo.pedrojoya.pr097.ui.viewmodel.ViewModelActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val testRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun shouldStartSaveActivityWhenSaveButtonClicked() {
        clickOn(R.id.btnSave)
        intended(hasComponent(SaveActivity::class.java.name))
    }

    @Test
    fun shouldStartRetainActivityWhenRetainButtonClicked() {
        clickOn(R.id.btnRetain)
        intended(hasComponent(RetainActivity::class.java.name))
    }

    @Test
    fun shouldStartStateActivityWhenStateButtonClicked() {
        clickOn(R.id.btnState)
        intended(hasComponent(AndroidStateActivity::class.java.name))
    }

    @Test
    fun shouldStartViewModelActivityWhenViewModelButtonClicked() {
        clickOn(R.id.btnViewModel)
        intended(hasComponent(ViewModelActivity::class.java.name))
    }

}
