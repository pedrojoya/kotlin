package pedrojoya.iessaladillo.es.pr225.ui.main

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr225.R
import pedrojoya.iessaladillo.es.pr225.base.MessageManager
import pedrojoya.iessaladillo.es.pr225.base.SnackbarManager
import pedrojoya.iessaladillo.es.pr225.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr225.data.local.Database
import pedrojoya.iessaladillo.es.pr225.extensions.viewModelProvider

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider { MainActivityViewModel(RepositoryImpl(Database)) }
    private val messageManager: MessageManager by lazy { SnackbarManager(lblEmptyView) }
    private val listAdapter: MainActivityAdapter by lazy { MainActivityAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        loadData()
    }

    private fun setupViews() {
        setupFab()
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupFab() {
        fab.setOnClickListener {
            messageManager.showMessage(getString(R.string
                    .main_fabClicked))
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            lblEmptyView.visibility = View.INVISIBLE
            layoutManager = GridLayoutManager(this@MainActivity,
                    resources.getInteger(R.integer.main_lstStudents_columns))
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun loadData() {
        val students = viewModel.students
        listAdapter.submitList(students)
        lblEmptyView.visibility = if (students.isEmpty()) VISIBLE else INVISIBLE
    }

}
