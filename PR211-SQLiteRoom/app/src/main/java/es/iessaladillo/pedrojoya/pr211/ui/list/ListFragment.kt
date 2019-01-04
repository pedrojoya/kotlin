package es.iessaladillo.pedrojoya.pr211.ui.list

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr211.data.local.AppDatabase
import es.iessaladillo.pedrojoya.pr211.extensions.doOnSwiped
import es.iessaladillo.pedrojoya.pr211.ui.student.StudentFragment
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class ListFragment : Fragment() {

    private val listAdapter: ListFragmentAdapter by lazy { ListFragmentAdapter() }
    private val viewModel: ListFragmentViewModel by viewModels {
        ListFragmentViewModelFactory(RepositoryImpl(AppDatabase.getInstance(requireContext()).studentDao()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Objects.requireNonNull(view)
        setupViews()
        observeStudents()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        setupRecyclerView()
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isEmpty()) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun setupToolbar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.app_name)
        }
    }

    private fun setupFab() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { navigateToAddStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            doOnSwiped { viewHolder ->
                viewModel.deleteStudent(listAdapter.getItem(viewHolder.adapterPosition)) }
        }
    }

    private fun navigateToAddStudent() {
        requireFragmentManager().commit {
            replace(R.id.flContent, StudentFragment.newInstance(), StudentFragment::class.java.simpleName)
            addToBackStack(StudentFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    companion object {

        fun newInstance(): ListFragment = ListFragment()

    }

}
