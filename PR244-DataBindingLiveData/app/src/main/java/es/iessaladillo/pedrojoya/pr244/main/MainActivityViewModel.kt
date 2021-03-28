package es.iessaladillo.pedrojoya.pr244.main

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import es.iessaladillo.pedrojoya.pr244.R
import es.iessaladillo.pedrojoya.pr244.base.Event
import java.util.*

private const val PHOTO_BASE_URL = "https://dummyimage" + ".com/356x200/deb4de/ffffff&text="

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val random = Random()
    private val resources: Resources = application.resources
    private val treatments: Array<String> = application.resources.getStringArray(R.array
            .activity_main_treatments)

    val name = MutableLiveData<String>("")
    val polite = MutableLiveData<Boolean>(false)
    val treatmentIndex = MutableLiveData<Int>(0)
    val photoUrl = MutableLiveData<String>(PHOTO_BASE_URL + random.nextInt(100))
    val valid = name.map { it.isNullOrBlank().not() }
    val lblNameText: LiveData<String> = name.map { buildNameLabel(it) }
    val viewMessage = MutableLiveData<Event<String>>()

    private fun buildNameLabel(name: String): String =
            if (name.isBlank()) {
                resources.getString(
                        R.string.main_lblName_required)
            } else {
                resources.getString(
                        R.string.main_lblName)
            }

    fun greet() {
        viewMessage.value = Event(buildMessage())
    }

    fun done(): Boolean {
        if (name.value.isNullOrBlank().not()) {
            greet()
        }
        return true
    }

    fun insult(): Boolean {
        viewMessage.value = Event(resources.getString(R.string.main_insult, name.value))
        return true
    }

    fun changePhoto() {
        photoUrl.value = PHOTO_BASE_URL + random.nextInt(100)
    }

    private fun buildMessage(): String =
            if (polite.value == true) {
                resources.getString(R.string.main_polite_greet, treatments[treatmentIndex.value!!], name.value)
            } else {
                resources.getString(R.string.main_greet, name.value)
            }

}

