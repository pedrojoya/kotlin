package pedrojoya.iessaladillo.es.pr104.ui.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pedrojoya.iessaladillo.es.pr104.R


class SecondaryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_secondary, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.secondary_title)
        }
    }

    companion object {

        fun newInstance() = SecondaryFragment()

    }

}
