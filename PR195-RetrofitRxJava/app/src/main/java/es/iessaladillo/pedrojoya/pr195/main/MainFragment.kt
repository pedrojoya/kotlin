package es.iessaladillo.pedrojoya.pr195.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr195.R
import es.iessaladillo.pedrojoya.pr195.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr195.data.model.Student
import es.iessaladillo.pedrojoya.pr195.data.remote.ApiService
import es.iessaladillo.pedrojoya.pr195.extensions.toast
import es.iessaladillo.pedrojoya.pr195.extensions.viewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy { MainFragmentAdapter().apply {
        emptyView = lblEmptyView }
    }
    private val viewModel: MainFragmentViewModel by viewModelProvider {
        MainFragmentViewModel(RepositoryImpl(ApiService.getInstance(requireContext().applicationContext).api))
    }
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        loadStudents(false)
    }

    private fun initViews() {
        setupPanel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL,
                    false)
            itemAnimator = DefaultItemAnimator()
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
        listAdapter.submitList(students)
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    private fun showErrorLoadingStudents(throwable: Throwable) {
        toast(getString(R.string.main_activity_error_loading_students, throwable.message))
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
