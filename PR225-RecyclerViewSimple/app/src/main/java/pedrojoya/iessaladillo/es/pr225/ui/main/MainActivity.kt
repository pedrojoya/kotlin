package pedrojoya.iessaladillo.es.pr225.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr225.R
import pedrojoya.iessaladillo.es.pr225.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr225.data.local.Database
import pedrojoya.iessaladillo.es.pr225.extensions.snackbar
import pedrojoya.iessaladillo.es.pr225.extensions.viewModelProvider

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider { MainActivityViewModel(RepositoryImpl(Database)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { snackbar(R.string.activity_main_fabClicked) }
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            lblEmpty.visibility = View.INVISIBLE
            adapter = MainActivityAdapter(viewModel.data)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity,
                    RecyclerView.VERTICAL, false)
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        }
    }

}
