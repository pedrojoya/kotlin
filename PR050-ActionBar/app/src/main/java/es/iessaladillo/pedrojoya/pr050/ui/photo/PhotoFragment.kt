package es.iessaladillo.pedrojoya.pr050.ui.photo

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr050.R
import es.iessaladillo.pedrojoya.pr050.ui.info.InfoFragment
import es.iessaladillo.pedrojoya.pr050.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

    private val viewModel: PhotoFragmentViewModel by viewModels {
        PhotoFragmentViewModelFactory(R.id.mnuOriginal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_photo, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
        observeEffectId()
    }

    private fun observeEffectId() {
        viewModel.effectId.observe(viewLifecycleOwner, Observer(this@PhotoFragment::setCorrectBitmap))
    }

    private fun setupActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.photo_title)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_photo, menu)
        viewModel.effectId.value?.let {
            menu.findItem(it)?.isChecked = true
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.mnuInfo -> {
                navigateToInfo()
                true
            }
            R.id.mnuOriginal, R.id.mnuGrey, R.id.mnuSepia, R.id.mnuBinary, R.id.mnuInverted -> {
                item.isChecked = true
                viewModel.changeEffect(item.itemId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun navigateToInfo() {
        requireFragmentManager().commit {
            replace(R.id.flContent, InfoFragment.newInstance(), InfoFragment::class.java.simpleName)
            addToBackStack(InfoFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }
    }

    private fun setCorrectBitmap(itemId: Int) {
        imgPhoto.drawable.colorFilter = when (itemId) {
            R.id.mnuGrey -> GREY_COLOR_FILTER
            R.id.mnuSepia -> SEPIA_COLOR_FILTER
            R.id.mnuBinary -> BINARY_COLOR_FILTER
            R.id.mnuInverted -> INVERTED_COLOR_FILTER
            else -> null
        }
    }

    companion object {

        fun newInstance(): PhotoFragment = PhotoFragment()

    }

}
