package es.iessaladillo.pedrojoya.pr100.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.iessaladillo.pedrojoya.pr100.R
import java.util.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val students: ArrayList<String> = ArrayList(Arrays.asList(*application.resources.getStringArray(R.array.alumnos)))

    fun deleteStudent(position: Int) {
        students.removeAt(position)
    }

}
