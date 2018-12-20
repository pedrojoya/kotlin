package es.iessaldillo.pedrojoya.pr160.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import es.iessaldillo.pedrojoya.pr160.R
import es.iessaldillo.pedrojoya.pr160.base.OnFabClickListener
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment : Fragment(), OnFabClickListener {

    private val viewModel: PageFragmentViewModel by viewModels { PageFragmentViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_page, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        lblLikes.text = viewModel.likes.toString()
    }

    override fun onFabClick(view: View) {
        like()
    }

    private fun like() {
        viewModel.likes++
        lblLikes.text = viewModel.likes.toString()
    }

    companion object {

        fun newInstance() = PageFragment()

    }

}
