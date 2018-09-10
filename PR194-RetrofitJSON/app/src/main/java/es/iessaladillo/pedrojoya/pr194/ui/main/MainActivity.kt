package es.iessaladillo.pedrojoya.pr194.ui.main

import android.os.Bundle
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr194.R
import es.iessaladillo.pedrojoya.pr194.base.Event
import es.iessaladillo.pedrojoya.pr194.base.RequestState
import es.iessaladillo.pedrojoya.pr194.data.model.Student
import es.iessaladillo.pedrojoya.pr194.data.remote.ApiService
import es.iessaladillo.pedrojoya.pr194.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listAdapter: MainActivityAdapter by lazy { MainActivityAdapter() }
    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(ApiService.getInstance(applicationContext).api)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        viewModel.students.observe(this, Observer{ request ->
            @Suppress("UNCHECKED_CAST")
            when (request) {
                is RequestState.Loading -> swlPanel.post { swlPanel.isRefreshing = request.isLoading }
                is RequestState.Error -> showErrorLoadingStudents(request.exception)
                is RequestState.Result<*> -> showStudents((request as RequestState.Result<List<Student>>).data)
            }
        })
    }

    private fun initViews() {
        setupListView()
        setupPanel()
    }

    private fun setupListView() {
        lstStudents.run {
            emptyView = lblEmptyView
            // In order to work well with SwipeRefreshLayout.
            setOnScrollListener(object : AbsListView.OnScrollListener {
                override fun onScrollStateChanged(view: AbsListView, scrollState: Int) { }

                override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                    val topRowVerticalPosition = getChildAt(0)?.top?:0
                    swlPanel.isEnabled = firstVisibleItem == 0 && topRowVerticalPosition >= 0
                }
            })
            // ---
            adapter = listAdapter
        }
    }

    private fun setupPanel() {
        swlPanel.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        swlPanel.setOnRefreshListener { viewModel.forceLoadStudents() }
    }

    private fun showStudents(students: List<Student>) {
        listAdapter.data = students
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    private fun showErrorLoadingStudents(event: Event<Exception>) {
        val exception = event.getContentIfNotHandled()
        if (exception != null) {
            Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
        }
    }

}
