package pedrojoya.iessaladillo.es.pr201.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr201.R
import pedrojoya.iessaladillo.es.pr201.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr201.base.setOnItemLongClickListener
import pedrojoya.iessaladillo.es.pr201.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr201.data.local.Database
import pedrojoya.iessaladillo.es.pr201.data.local.model.Student
import pedrojoya.iessaladillo.es.pr201.extensions.viewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter().apply {
            setOnItemClickListener { _, position -> updateStudent(getItem(position)) }
            setOnItemLongClickListener { _, position ->
                deleteStudent(getItem(position))
                true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        observeStudents()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
        setupFab()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupFab() {
        fab.setOnClickListener { addStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = GridLayoutManager(this@MainActivity,
                    resources.getInteger(R.integer.main_lstStudents_columns))
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun observeStudents() {
        viewModel.getStudents().observe(this, Observer { students ->
            listAdapter.submitList(students)
        })
        viewModel.isListEmpty().observe(this, Observer { isEmpty ->
            lblEmptyView.visibility = if (isEmpty) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun addStudent() {
        viewModel.insertStudent(Database.newFakeStudent())
    }

    private fun updateStudent(student: Student) {
        val newStudent = student.copy().apply {
            name = reverseName()
        }
        viewModel.updateStudent(student, newStudent)
    }

    private fun deleteStudent(student: Student) {
        viewModel.deleteStudent(student)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSort) {
            toggleOrder()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val mnuSort = menu?.findItem(R.id.mnuSort)
        val desc = viewModel.isInDescendentOrder()
        mnuSort?.setIcon(
                if (desc) R.drawable.ic_sort_ascending_black_24dp
                else R.drawable.ic_sort_descending_black_24dp)
        return super.onPrepareOptionsMenu(menu)
    }

    private fun toggleOrder() {
        viewModel.toggleOrder()
        invalidateOptionsMenu()
    }

}
