package es.iessaladillo.pedrojoya.pr178.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr178.data.Database
import es.iessaladillo.pedrojoya.pr178.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr178.data.Student
import es.iessaladillo.pedrojoya.pr178.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_BOTTOMSHEET_FRAGMENT = "TAG_BOTTOMSHEET_FRAGMENT"

class MainActivity : AppCompatActivity() {

    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter(viewModel.students as ArrayList<Student>).apply {
            setOnItemClickListener { _, item, _, _ -> showBottomSheetDialogFragment(item) }
            emptyView = lblEmptyView
        }
    }
    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(this@MainActivity, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL))
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        }
    }

    private fun showBottomSheetDialogFragment(student: Student) {
        MenuBottomSheetDialogFragment.newInstance(student)
                .show(supportFragmentManager, TAG_BOTTOMSHEET_FRAGMENT)
    }

}
