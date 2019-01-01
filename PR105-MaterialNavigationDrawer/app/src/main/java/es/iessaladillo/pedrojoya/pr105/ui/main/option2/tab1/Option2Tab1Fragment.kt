package es.iessaladillo.pedrojoya.pr105.ui.main.option2.tab1


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr105.data.local.Database
import es.iessaladillo.pedrojoya.pr105.extensions.snackbar
import kotlinx.android.synthetic.main.fragment_option2_tab1.*

class Option2Tab1Fragment : Fragment() {

    private val fab: FloatingActionButton by lazy {
        ViewCompat.requireViewById<FloatingActionButton>(requireParentFragment().requireView(), R.id.fab)
    }
    private val listAdapter = Option2Tab1Adapter()
    private val viewModel: Option2Tab1ViewModel by viewModels {
        Option2Tab1ViewModelFactory(RepositoryImpl(Database))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_option2_tab1, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeStudents()
    }

    private fun setupViews() {
        setupFab()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupFab() {
        fab.setOnClickListener { showMessage() }
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { listAdapter.submitList(it) })
    }

    private fun showMessage() {
        fab.snackbar(R.string.option2_tab1_fragment_fab_clicked)
    }

}
