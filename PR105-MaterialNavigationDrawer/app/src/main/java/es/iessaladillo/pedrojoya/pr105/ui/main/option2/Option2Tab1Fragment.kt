package es.iessaladillo.pedrojoya.pr105.ui.main.option2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.data.local.Database
import kotlinx.android.synthetic.main.fragment_option2.*
import kotlinx.android.synthetic.main.fragment_option2_tab1.*

class Option2Tab1Fragment : Fragment() {

    private lateinit var mAdapter: Option2Tab1Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_option2_tab1, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupFab()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = Option2Tab1Adapter(Database.queryStudents())
        lstStudents.run {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupFab() {
        val fab: FloatingActionButton = ViewCompat.requireViewById(parentFragment!!.view!!, R.id
                .fab)
        fab.setOnClickListener { showMessage() }
    }

    private fun showMessage() {
        Snackbar.make(fab, R.string.option2_tab1_fragment_fab_clicked, Snackbar.LENGTH_SHORT)
                .show()
    }

}
