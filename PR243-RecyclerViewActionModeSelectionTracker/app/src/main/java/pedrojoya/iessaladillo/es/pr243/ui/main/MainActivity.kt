package pedrojoya.iessaladillo.es.pr243.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr243.R
import pedrojoya.iessaladillo.es.pr243.base.MultiChoiceModeListener
import pedrojoya.iessaladillo.es.pr243.base.PositionalDetailsLookup
import pedrojoya.iessaladillo.es.pr243.base.PositionalItemKeyProvider
import pedrojoya.iessaladillo.es.pr243.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr243.data.local.Database
import pedrojoya.iessaladillo.es.pr243.data.local.model.Student
import pedrojoya.iessaladillo.es.pr243.extensions.viewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val selectionTracker: SelectionTracker<Long> by lazy {
        SelectionTracker.Builder(
                "my-position-selection",
                lstStudents,
                PositionalItemKeyProvider(),
                PositionalDetailsLookup(lstStudents),
                StorageStrategy.createLongStorage())
                .build().apply {
                    addObserver(object : SelectionTracker.SelectionObserver<Long>() {
                        override fun onSelectionChanged() {
                            super.onSelectionChanged()
                            if (actionMode != null) {
                                if (selectionTracker.hasSelection()) {
                                    // Se informa de que ha cambiado la selección.
                                    multiChoiceModeListener.onSelectionChanged(actionMode!!, selectionTracker.selection.size())
                                } else {
                                    // Si no hay selección se finaliza el actionMode.
                                    actionMode!!.finish()
                                    actionMode = null
                                }
                            } else {
                                // Si hay selección, se inicia el actionMode.
                                if (selectionTracker.hasSelection()) {
                                    actionMode = startSupportActionMode(multiChoiceModeListener)
                                }
                            }
                        }
                    })
                }
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter().apply {
            emptyView = lblEmpty
            setOnItemClickListener { _, position ->
                if (actionMode == null) {
                    showStudent(listAdapter.getItem(position))
                }
            }
        }
    }
    private var actionMode: ActionMode? = null
    private val multiChoiceModeListener = object : MultiChoiceModeListener {
        override fun onCreateActionMode(actionMode: ActionMode, menu: Menu): Boolean {
            viewModel.isInActionMode = true
            actionMode.menuInflater.inflate(R.menu.activity_main_contextual, menu)
            return true
        }

        override fun onPrepareActionMode(actionMode: ActionMode, menu: Menu): Boolean {
            updateSelectedCountDisplay(actionMode)
            return true
        }

        override fun onActionItemClicked(actionMode: ActionMode, menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.mnuDelete -> {
                    deleteStudents()
                    return true
                }
            }
            return false
        }

        override fun onDestroyActionMode(actionMode: ActionMode) {
            selectionTracker.clearSelection()
            viewModel.isInActionMode = false
        }

        private fun updateSelectedCountDisplay(actionMode: ActionMode) {
            val count = selectionTracker.selection.size()
            actionMode.title = resources.getQuantityString(R.plurals.selected, count, count)
        }

        override fun onSelectionChanged(actionMode: ActionMode, selected: Int) {
            updateSelectedCountDisplay(actionMode)
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
        if (viewModel.isInActionMode) {
            actionMode = startSupportActionMode(multiChoiceModeListener)
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
        fab.setOnClickListener { _ ->
            selectionTracker.clearSelection()
            actionMode?.finish()
            actionMode = null
            addStudent()
        }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize (true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
        listAdapter.submitList(viewModel.getStudents(false))
        // Creamos el selectionTracker y se lo asignamos al adaptador.
        // DEBE HACERSE SIEMPRE DESPUÉS DE HABER ASIGNADO EL ADAPTADOR AL RECYCLERVIEW.
        listAdapter.selectionTracker = selectionTracker
    }

    private fun addStudent() {
        viewModel.addStudent(Database.newFakeStudent())
        listAdapter.submitList(viewModel.getStudents(true))
        lstStudents.scrollToPosition(listAdapter.itemCount - 1)
    }

    private fun showStudent(student: Student) {
        Snackbar.make(lstStudents!!,
                getString(R.string.main_activity_click_on_student, student.name),
                Snackbar.LENGTH_SHORT).show()
    }


    private fun deleteStudents() {
        selectionTracker.selection.forEach {
            viewModel.deleteStudent(listAdapter.getItem((it as Long).toInt()))
        }
        selectionTracker.clearSelection()
        listAdapter.submitList(viewModel.getStudents(true))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker.onSaveInstanceState(outState)
    }

}
