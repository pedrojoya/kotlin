package es.iessaladillo.pedrojoya.pr212.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr212.R
import es.iessaladillo.pedrojoya.pr212.data.Repository
import es.iessaladillo.pedrojoya.pr212.data.model.Student
import es.iessaladillo.pedrojoya.pr212.extensions.findFragmentByTag
import es.iessaladillo.pedrojoya.pr212.extensions.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        loadFragment()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun loadFragment() {
        if (findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            replaceFragment(R.id.flContent, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
        }
    }

}

internal class MainActivityViewModel(private val repository: Repository) : ViewModel() {
    private var students: List<Student>? = null

    fun getStudents(forceLoad: Boolean): List<Student> {
        if (forceLoad) {
            students = repository.getStudents()
        }
        return students!!
    }

}

internal class MainActivityViewModelFactory(private val repository: Repository) :
        ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }

}

