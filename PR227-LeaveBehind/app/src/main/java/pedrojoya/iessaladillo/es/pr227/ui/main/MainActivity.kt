package pedrojoya.iessaladillo.es.pr227.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr227.R
import pedrojoya.iessaladillo.es.pr227.base.IconicDrawable
import pedrojoya.iessaladillo.es.pr227.base.LeaveBehindCallback
import pedrojoya.iessaladillo.es.pr227.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr227.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr227.data.local.Database
import pedrojoya.iessaladillo.es.pr227.data.local.model.Student
import pedrojoya.iessaladillo.es.pr227.data.local.newFakeStudent
import pedrojoya.iessaladillo.es.pr227.extensions.snackbar
import pedrojoya.iessaladillo.es.pr227.extensions.viewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter(viewModel.students as ArrayList<Student>).apply {
            setOnItemClickListener { _, position ->
                editStudent(position)
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
        val leaveBehindCallback = object : LeaveBehindCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {
                    deleteStudent(viewHolder.adapterPosition)
                } else {
                    archiveStudent(viewHolder.adapterPosition)
                }

            }
        }.apply {
            withRightIconicDrawable(
                    IconicDrawable(ContextCompat.getColor(this@MainActivity, R.color.delete),
                            ContextCompat.getDrawable(this@MainActivity, R.drawable
                                    .ic_delete_white_24dp)!!))
            withLeftIconicDrawable(
                    IconicDrawable(ContextCompat.getColor(this@MainActivity, R.color.archive),
                            ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_archive_white_24dp)!!))
        }
        val itemTouchHelper = ItemTouchHelper(leaveBehindCallback)

        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,
                    RecyclerView.VERTICAL, false)
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun archiveStudent(position: Int) {
        lstStudents.snackbar(getString(R.string.main_activity_archive_student, listAdapter.getItem(position).name))
        deleteStudent(position)
    }

    private fun deleteStudent(position: Int) {
        viewModel.deleteStudent(listAdapter.getItem(position))
        listAdapter.notifyItemRemoved(position)
    }

    private fun addStudent() {
        viewModel.addStudent(newFakeStudent())
        listAdapter.notifyItemInserted(listAdapter.itemCount - 1)
        lstStudents.scrollToPosition(listAdapter.itemCount - 1)
    }

    private fun editStudent(position: Int) {
        lstStudents.snackbar(getString(R.string.main_activity_click_on_student, listAdapter.getItem(position).name))
    }

}
