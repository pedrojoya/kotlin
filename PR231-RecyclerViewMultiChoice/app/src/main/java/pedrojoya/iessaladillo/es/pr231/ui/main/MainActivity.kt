package pedrojoya.iessaladillo.es.pr231.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr231.base.PositionalDetailsLookup
import pedrojoya.iessaladillo.es.pr231.base.PositionalItemKeyProvider
import pedrojoya.iessaladillo.es.pr231.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr231.data.local.Database
import pedrojoya.iessaladillo.es.pr231.extensions.snackbar
import pedrojoya.iessaladillo.es.pr231.extensions.viewModelProvider
import pedrojoya.iessaladillo.es.pr331.R


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val selectionTracker: SelectionTracker<Long> by lazy {
        SelectionTracker.Builder(
                "my-position-selection",
                lstStudents,
                PositionalItemKeyProvider(),
                // StableIdKeyProvider usa el id del item como key.
                //new StableIdKeyProvider(lstStudents),
                PositionalDetailsLookup(lstStudents!!),
                // Las claves son long.
                StorageStrategy.createLongStorage())
                .build()
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter().apply {
            emptyView = lblEmpty
            setOnItemClickListener { _, position ->
                if (!selectionTracker!!.hasSelection()) {
                    selectionTracker!!.select(position.toLong())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        // Debe recuperarse el estado del selectionTracker una vez haya sido creado,
        // por lo que no se puede hacer en onRestoreInstanceState().
        if (savedInstanceState != null) {
            selectionTracker.onRestoreInstanceState(savedInstanceState)
        }
    }

    private fun initViews() {
        setupToolbar()
        setupRecyclerView()
        setupFab()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setupFab() {
        fab.setOnClickListener { _ -> showSelectedStudents() }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
        listAdapter.submitList(viewModel.getStudents(false))
        // LA ASIGNACIÓN DEL TRACKER DEBE HACERSE SIEMPRE DESPUÉS DE HABER ASIGNADO EL ADAPTADOR AL
        // RECYCLERVIEW.
        listAdapter.selectionTracker = selectionTracker
    }

    private fun showSelectedStudents() {
        val selectedNames = StringBuilder()
        for (key in selectionTracker.selection) {
            selectedNames.append(listAdapter.getItem((key as Long).toInt()).name)
            selectedNames.append(", ")
        }
        val message = selectedNames.toString()
        if (message.isNotBlank()) {
            lstStudents.snackbar(getString(R.string.main_activity_students_selected, message))
        } else {
            lstStudents.snackbar(getString(R.string.main_activity_no_students_selected))
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker.onSaveInstanceState(outState)
    }

}
