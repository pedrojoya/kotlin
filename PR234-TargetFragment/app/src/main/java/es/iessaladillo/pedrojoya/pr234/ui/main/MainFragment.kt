package es.iessaladillo.pedrojoya.pr234.ui.main


import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.pr234.R
import es.iessaladillo.pedrojoya.pr234.base.YesNoDialogFragment
import kotlinx.android.synthetic.main.fragment_main.*

private const val TAG_DIALOG_FRAGMENT = "TAG_DIALOG_FRAGMENT"
private const val RC_DIALOG_FRAGMENT = 1

class MainFragment : Fragment(), YesNoDialogFragment.Listener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {
        btnDelete.setOnClickListener { showConfirmationDialog() }
    }

    private fun showConfirmationDialog() {
        YesNoDialogFragment.newInstance(
                getString(R.string.main_fragment_confirm_deletion),
                getString(R.string.main_fragment_sure), getString(R.string.main_fragment_yes),
                getString(R.string.main_fragment_no)).also {
            it.setTargetFragment(this@MainFragment, RC_DIALOG_FRAGMENT)
            it.show(requireActivity().supportFragmentManager, TAG_DIALOG_FRAGMENT)
        }
    }

    override fun onPositiveButtonClick(dialog: DialogInterface) {
        Snackbar.make(btnDelete, getString(R.string.main_fragment_delete), Snackbar.LENGTH_SHORT).show()
    }

    override fun onNegativeButtonClick(dialog: DialogInterface) {
        Snackbar.make(btnDelete, getString(R.string.main_fragment_no_delete), Snackbar.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance() = MainFragment()

    }

}
