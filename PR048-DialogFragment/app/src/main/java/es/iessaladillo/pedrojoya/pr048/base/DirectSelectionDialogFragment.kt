package es.iessaladillo.pedrojoya.pr048.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.pr048.extensions.extraCharSequenceArray
import es.iessaladillo.pedrojoya.pr048.extensions.extraString

private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_ITEMS = "ARG_ITEMS"

class DirectSelectionDialogFragment : DialogFragment() {

    private lateinit var listener: Listener
    private val title by extraString(ARG_TITLE)
    private val items by extraCharSequenceArray(ARG_ITEMS)

    interface Listener {
        fun onItemSelected(dialog: DialogFragment, which: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setItems(items) { _, which ->
            listener.onItemSelected(this@DirectSelectionDialogFragment, which)
        }
        .create()


    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        listener = try {
            if (targetFragment != null) {
                targetFragment as Listener
            } else {
                activity as Listener
            }
        } catch (e: ClassCastException) {
            throw ClassCastException(
                    "Listener must implement DirectSelectionDialogFragment.Listener")
        }

    }

    companion object {

        fun newInstance(title: String, items: Array<String>): DirectSelectionDialogFragment =
            DirectSelectionDialogFragment().apply {
                arguments = bundleOf(
                        ARG_TITLE to title,
                        ARG_ITEMS to items
                )
            }

    }

}