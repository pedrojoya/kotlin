package es.iessaladillo.pedrojoya.pr146.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr146.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        btnShowDetail.setOnClickListener { navigateToDetail() }
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun navigateToDetail() {
        requireFragmentManager().commit {
            replace(R.id.flContent,
                    DetailFragment.newInstance(), DetailFragment::class.java.simpleName)
            addToBackStack(DetailFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_NONE)
        }
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
