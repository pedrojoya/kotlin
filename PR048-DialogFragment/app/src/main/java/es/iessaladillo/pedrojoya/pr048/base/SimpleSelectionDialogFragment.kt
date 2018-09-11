package es.iessaladillo.pedrojoya.pr048.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.pr048.extensions.extraCharSequenceArray
import es.iessaladillo.pedrojoya.pr048.extensions.extraInt
import es.iessaladillo.pedrojoya.pr048.extensions.extraString

private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_ITEMS = "ARG_ITEMS"
private const val ARG_CONFIRM_TEXT = "ARG_CONFIRM_TEXT"
private const val ARG_DEFAULT_SELECTED_INDEX = "ARG_DEFAULT_SELECTED_INDEX"

class SimpleSelectionDialogFragment : DialogFragment() {

    private lateinit var listener: Listener
    private val title by extraString(ARG_TITLE)
    private val items: Array<CharSequence> by extraCharSequenceArray(ARG_ITEMS)
    private val confirmText by extraString(ARG_CONFIRM_TEXT)
    private val defaultSelectedIndex by extraInt(ARG_DEFAULT_SELECTED_INDEX)
    private var selectedIndex = 0

    interface Listener {
        fun onConfirmSelection(dialog: DialogInterface, which: Int)

        fun onItemSelected(dialog: DialogInterface, which: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        selectedIndex = defaultSelectedIndex
        return AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setSingleChoiceItems(items, defaultSelectedIndex) { dialog, which ->
                    selectedIndex = which
                    listener.onItemSelected(dialog, which)
                }
                .setPositiveButton(confirmText) { dialog, _ ->
                    dialog.dismiss()
                    listener.onConfirmSelection(dialog, selectedIndex)
                }
                .create()
    }

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
                    "Listener must implement SimpleSelectionDialogFragment.Listener")
        }

    }

    companion object {

        fun newInstance(title: String, items: Array<String>,
                        confirmText: String, defaultSelectedIndex: Int):
                SimpleSelectionDialogFragment =
            SimpleSelectionDialogFragment().apply {
                arguments = bundleOf(
                        ARG_TITLE to title,
                        ARG_ITEMS to items,
                        ARG_CONFIRM_TEXT to confirmText,
                        ARG_DEFAULT_SELECTED_INDEX to defaultSelectedIndex
                )
            }

    }

}