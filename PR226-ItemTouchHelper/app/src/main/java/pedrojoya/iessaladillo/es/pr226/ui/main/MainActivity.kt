package pedrojoya.iessaladillo.es.pr226.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr226.R
import pedrojoya.iessaladillo.es.pr226.base.setOnItemClickListener
import pedrojoya.iessaladillo.es.pr226.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr226.data.local.Database
import pedrojoya.iessaladillo.es.pr226.data.local.model.Student
import pedrojoya.iessaladillo.es.pr226.data.local.newFakeStudent
import pedrojoya.iessaladillo.es.pr226.extensions.onTouch
import pedrojoya.iessaladillo.es.pr226.extensions.snackbar
import pedrojoya.iessaladillo.es.pr226.extensions.viewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter(viewModel.students as ArrayList<Student>).apply {
            setOnItemClickListener { view, position ->
                snackbar(view, getString(R.string.main_click_on_student, getItem(position).name))
            }
            emptyView = lblEmpty
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
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
            layoutManager = LinearLayoutManager(this@MainActivity,
                    RecyclerView.VERTICAL, false)
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            onTouch(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT) {
                onMove { _, viewHolder, target ->
                    val fromPos = viewHolder.adapterPosition
                    val toPos = target.adapterPosition
                    listAdapter.swapItems(fromPos, toPos)
                    true
                }
                onSwiped { viewHolder, _ ->
                    val position = viewHolder.adapterPosition
                    viewModel.deleteStudent(listAdapter.getItem(position))
                    listAdapter.notifyItemRemoved(position)
                }
            }
        }
    }

    private fun addStudent() {
        viewModel.addStudent(newFakeStudent())
        listAdapter.notifyItemInserted(listAdapter.itemCount - 1)
        lstStudents.scrollToPosition(listAdapter.itemCount - 1)
    }

}
