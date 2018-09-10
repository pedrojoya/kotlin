package pedrojoya.iessaladillo.es.pr229.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr229.R
import pedrojoya.iessaladillo.es.pr229.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr229.base.setOnItemLongClickListener
import pedrojoya.iessaladillo.es.pr229.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr229.data.local.Database
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student
import pedrojoya.iessaladillo.es.pr229.extensions.viewModelProvider
import pedrojoya.iessaladillo.es.pr229.old.OnItemClickListener
import pedrojoya.iessaladillo.es.pr229.old.OnItemLongClickListener


class MainActivity : AppCompatActivity(), OnItemClickListener<Student>, OnItemLongClickListener<Student> {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter().apply {
            emptyView = lblEmptyView
            setOnItemClickListener { _, position -> updateStudent(getItem(position)) }
            setOnItemLongClickListener { _, position ->
                deleteStudent(getItem(position))
                true
            }
        }
    }

    private var currentStudents: List<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        viewModel.students.observe(this, Observer { students ->
            currentStudents = students
            listAdapter.submitList(
                    if (viewModel.order == 1) students.sortedBy { it.name }
                    else students.sortedByDescending { it.name }
            )
        })
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
        fab.setOnClickListener { _ -> addStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun addStudent() {
        viewModel.addStudent(Database.newFakeStudent())
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
            viewModel.toggleOrder()
            currentStudents?.run {
                listAdapter.submitList(
                        if (viewModel.order == 1) currentStudents!!.sortedBy { it.name }
                        else currentStudents!!.sortedByDescending { it.name }
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onItemClick(view: View, item: Student, position: Int, id: Long) {
        updateStudent(item)
    }

    override fun onItemLongClick(view: View, item: Student, position: Int, id: Long): Boolean {
        deleteStudent(item)
        return true
    }

}
