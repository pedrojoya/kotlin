package pedrojoya.iessaladillo.es.pr106.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr106.R
import pedrojoya.iessaladillo.es.pr106.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr106.base.setOnItemLongClickListener
import pedrojoya.iessaladillo.es.pr106.data.Database
import pedrojoya.iessaladillo.es.pr106.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr106.data.Student
import pedrojoya.iessaladillo.es.pr106.data.newFakeStudent
import pedrojoya.iessaladillo.es.pr106.extensions.getViewModel
import pedrojoya.iessaladillo.es.pr106.extensions.snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
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
        mAdapter = MainActivityAdapter(viewModel.students as ArrayList<Student>).apply {
            setOnItemClickListener { view, item, _, _ ->
                snackbar(view, getString(R.string.main_activity_click_on_student, item.name))
            }
            setOnItemLongClickListener { _, item, position, _ ->
                viewModel.deleteStudent(item)
                this.notifyItemRemoved(position)
                true
            }
            emptyView = lblEmpty
        }
        lstStudents.run {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        }
    }

    private fun addStudent() {
        viewModel.addStudent(newFakeStudent())
        mAdapter.notifyItemInserted(mAdapter.itemCount - 1)
        lstStudents.scrollToPosition(mAdapter.itemCount - 1)
    }

}
