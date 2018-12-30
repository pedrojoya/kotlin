package es.iessaladillo.pedrojoya.pr249.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr249.R
import es.iessaladillo.pedrojoya.pr249.base.EventObserver
import es.iessaladillo.pedrojoya.pr249.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr249.data.local.Database
import es.iessaladillo.pedrojoya.pr249.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_main.*

class ListFragment : Fragment() {

    private val viewModel: ListFragmentViewModel by viewModels {
        ListFragmentViewModelFactory(RepositoryImpl(Database))
    }
    private val listAdapter: ListFragmentAdapter by lazy { ListFragmentAdapter(viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeStudents()
        observeNavigation()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.list_title)
        }
    }

    private fun setupRecyclerView() {
        lstItems.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun observeNavigation() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner, EventObserver { item -> navigateToDetail(item)})
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isNotEmpty()) View.INVISIBLE else View.VISIBLE
        })
    }

    private fun navigateToDetail(item: String) {
        requireFragmentManager().commit {
            replace(R.id.flContent, DetailFragment.newInstance(item), DetailFragment::class.java.simpleName)
            addToBackStack(DetailFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }

    }

}
