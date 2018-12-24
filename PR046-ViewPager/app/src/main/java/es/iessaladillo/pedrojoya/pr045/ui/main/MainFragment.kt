package es.iessaladillo.pedrojoya.pr045.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr045.R
import kotlinx.android.synthetic.main.fragment_main.*

private const val DEFAULT_PAGE = 2

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = MainFragmentAdapter()
        vpPages.adapter = adapter
        vpPages.currentItem = DEFAULT_PAGE
    }

    companion object {

        fun newInstance(): Fragment = MainFragment()

    }

}
