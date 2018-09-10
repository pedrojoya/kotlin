package es.iessaladillo.pedrojoya.pr195.main

import android.os.Bundle
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr195.R
import es.iessaladillo.pedrojoya.pr195.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr195.data.model.Student
import es.iessaladillo.pedrojoya.pr195.data.remote.ApiService
import es.iessaladillo.pedrojoya.pr195.extensions.viewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listAdapter: MainActivityAdapter by lazy { MainActivityAdapter() }
    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(ApiService.getInstance(applicationContext).api))
    }
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        loadStudents(false)
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
                override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}

                override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                    val topRowVerticalPosition = getChildAt(0)?.top ?: 0
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
        swlPanel.setOnRefreshListener { loadStudents(true) }
    }

    private fun loadStudents(forceLoad: Boolean = false) {
        compositeDisposable.add(viewModel.getStudents(forceLoad)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ showStudents(it) }, { showErrorLoadingStudents(it) }))
    }

    private fun showStudents(students: List<Student>) {
        listAdapter.data = students
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    private fun showErrorLoadingStudents(throwable: Throwable) {
        Toast.makeText(this,
                getString(R.string.main_activity_error_loading_students, throwable.message),
                Toast.LENGTH_LONG).show()
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
