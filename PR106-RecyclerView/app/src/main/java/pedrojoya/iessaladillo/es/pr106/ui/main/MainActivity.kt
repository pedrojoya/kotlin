package pedrojoya.iessaladillo.es.pr106.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr106.R
import pedrojoya.iessaladillo.es.pr106.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr106.base.setOnItemLongClickListener
import pedrojoya.iessaladillo.es.pr106.data.local.Database
import pedrojoya.iessaladillo.es.pr106.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr106.data.local.model.Student
import pedrojoya.iessaladillo.es.pr106.data.local.newFakeStudent
import pedrojoya.iessaladillo.es.pr106.extensions.snackbar
import pedrojoya.iessaladillo.es.pr106.extensions.viewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter(viewModel.students as ArrayList<Student>).apply {
            setOnItemClickListener { view, position ->
                snackbar(view, getString(R.string.main_activity_click_on_student, getItem(position).name))
            }
            setOnItemLongClickListener { _, position ->
                viewModel.deleteStudent(getItem(position))
                this.notifyItemRemoved(position)
                true
            }
            emptyView = lblEmpty
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
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
        fab.setOnClickListener { addStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,
                    RecyclerView.VERTICAL, false)
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        }
    }

    private fun addStudent() {
        viewModel.addStudent(newFakeStudent())
        listAdapter.notifyItemInserted(listAdapter.itemCount - 1)
        lstStudents.scrollToPosition(listAdapter.itemCount - 1)
    }

}
