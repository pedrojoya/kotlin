package pedrojoya.iessaladillo.es.pr201.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr201.R
import pedrojoya.iessaladillo.es.pr201.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr201.base.setOnItemLongClickListener
import pedrojoya.iessaladillo.es.pr201.data.Database
import pedrojoya.iessaladillo.es.pr201.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr201.data.Student
import pedrojoya.iessaladillo.es.pr201.extensions.getViewModel


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
        fabAccion.setOnClickListener { addStudent() }
    }

    private fun setupRecyclerView() {
        mAdapter = MainActivityAdapter(ArrayList()).apply {
            emptyView = lblEmptyView
            setOnItemClickListener { _, item, _, _ -> updateStudent(item) }
            setOnItemLongClickListener { _, item, _, _ ->
                deleteStudent(item)
                true
            }
        }
        lstStudents.run {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        }
        mAdapter.submitList(viewModel.getStudents(false))
    }

    private fun addStudent() {
        viewModel.addStudent(Database.newFakeStudent())
        mAdapter.submitList(viewModel.getStudents(true))
    }

    private fun updateStudent(student: Student) {
        val newStudent = student.copy().apply {
            name = reverseName()
        }
        viewModel.updateStudent(student, newStudent)
        mAdapter.submitList(viewModel.getStudents(true))
    }

    private fun deleteStudent(student: Student) {
        viewModel.deleteStudent(student)
        mAdapter.submitList(viewModel.getStudents(true))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuOrdenar) {
            viewModel.toggleOrder()
            mAdapter.submitList(viewModel.getStudents(true))
        }
        return super.onOptionsItemSelected(item)
    }

}
