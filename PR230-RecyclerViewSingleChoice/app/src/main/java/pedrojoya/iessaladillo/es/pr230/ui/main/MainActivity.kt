package pedrojoya.iessaladillo.es.pr230.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr230.R
import pedrojoya.iessaladillo.es.pr230.base.StableIdListAdapter
import pedrojoya.iessaladillo.es.pr230.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr230.data.local.Database
import pedrojoya.iessaladillo.es.pr230.data.local.model.Student
import pedrojoya.iessaladillo.es.pr230.extensions.snackbar
import pedrojoya.iessaladillo.es.pr230.extensions.viewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val selectionTracker: SelectionTracker<Long> by lazy {
        //        SelectionTracker.Builder(
//                "lstStudentsSelection",
//                lstStudents,
//                PositionalItemKeyProvider(),
//                PositionalDetailsLookup(lstStudents),
//                // Las claves son de tipo Long.
//                StorageStrategy.createLongStorage())
//                // Selección simple
//                .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
//                .build()

//        MainActivityAdapter2.newSelectionTrackerBuilder("lstStudentsSelection",
//                lstStudents, listAdapter)
//                .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
//                .build()

        StableIdListAdapter.newSingleSelectionTrackerBuilder("lstStudentsSelection",
                lstStudents)
                .build()

//        SelectionTracker.Builder(
//                "lstStudentsSelection",
//                lstStudents,
//                MainActivityAdapter2.KeyProvider(listAdapter),
//                MainActivityAdapter2.DetailsLookup(lstStudents),
//                // Las claves son de tipo Long.
//                StorageStrategy.createLongStorage())
//                // Selección simple
//                .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
//                .build()
    }
    //    private val listAdapter: MainActivityAdapter by lazy {
//        MainActivityAdapter().apply {
//            emptyView = lblEmpty
//            setOnItemClickListener { _, position ->
//                // La propiedad selectionTracker del adapter forzosamente debe tener un tipo
//                // nullable porque el selectionTracker debe ser asignado al adaptador más adelante,
//                // una vez que se ha asignado el adaptador al recyclerview.
//                if (!selectionTracker!!.hasSelection()) {
//                    selectionTracker!!.select(position.toLong())
//                }
//            }
//        }
//    }
    private val listAdapter: MainActivityAdapter3 = MainActivityAdapter3()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        // Debe recuperarse el estado del selectionTracker una vez haya sido creado,
        // por lo que no se puede hacer en onRestoreInstanceState().
        if (savedInstanceState != null) {
            selectionTracker.onRestoreInstanceState(savedInstanceState)
        }
    }

    private fun setupViews() {
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
        fab.setOnClickListener { showSelectedStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
        listAdapter.submitList(viewModel.getStudents(false))
        lblEmpty.visibility = View.INVISIBLE
        // LA ASIGNACIÓN DEL TRACKER DEBE HACERSE SIEMPRE DESPUÉS DE HABER ASIGNADO EL ADAPTADOR AL
        // RECYCLERVIEW.
        listAdapter.selectionTracker = selectionTracker
    }

    private fun showSelectedStudent() {
        if (selectionTracker.hasSelection()) {
            for (key in selectionTracker.selection) {
//                showStudent(listAdapter.getItem((key as Long).toInt()))
                showStudent(listAdapter.getItem(key))
            }

        } else {
            showNoStudentSelected()
        }
    }

    private fun showStudent(student: Student) {
        lstStudents.snackbar(getString(R.string.main_activity_student_selected, student.name))
    }

    private fun showNoStudentSelected() {
        lstStudents.snackbar(getString(R.string.main_activity_no_student_selected))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Se debe guardar el estado del selectionTracker al cambiar la configuración.
        selectionTracker.onSaveInstanceState(outState)
    }

}
