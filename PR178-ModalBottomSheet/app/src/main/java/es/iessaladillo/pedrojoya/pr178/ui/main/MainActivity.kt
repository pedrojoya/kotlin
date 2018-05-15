package es.iessaladillo.pedrojoya.pr178.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr178.data.Database
import es.iessaladillo.pedrojoya.pr178.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr178.data.Student
import es.iessaladillo.pedrojoya.pr178.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_BOTTOMSHEET_FRAGMENT = "TAG_BOTTOMSHEET_FRAGMENT"

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MainActivityAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        with (supportActionBar!!) {
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        mAdapter = MainActivityAdapter(viewModel.students as ArrayList<Student>).apply {
            setOnItemClickListener { _, item, _, _ -> showBottomSheetDialogFragment(item) }
            emptyView = lblEmptyView
        }
        with (lstStudents) {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun showBottomSheetDialogFragment(student: Student) {
        MenuBottomSheetDialogFragment.newInstance(student)
                .show(supportFragmentManager, TAG_BOTTOMSHEET_FRAGMENT)
    }

}
