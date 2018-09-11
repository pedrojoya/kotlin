package es.iessaladillo.pedrojoya.pr048.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.pr048.extensions.extraCharSequenceArray
import es.iessaladillo.pedrojoya.pr048.extensions.extraString

private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_ITEMS = "ARG_ITEMS"
private const val ARG_CONFIRM_TEXT = "ARG_CONFIRM_TEXT"
private const val ARG_DEFAULT_SELECTED_INDEXES = "ARG_DEFAULT_SELECTED_INDEXES"

class MultipleSelectionDialogFragment : DialogFragment() {

    private lateinit var listener: Listener
    private val title by extraString(ARG_TITLE)
    private val items by extraCharSequenceArray(ARG_ITEMS)
    private val confirmText by extraString(ARG_CONFIRM_TEXT)
    private lateinit var defaultItemsSelectedState: BooleanArray

    interface Listener {
        fun onConfirmSelection(dialog: DialogInterface, itemsSelectedState: BooleanArray)

        fun onItemClick(dialog: DialogInterface, which: Int, isChecked: Boolean)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        defaultItemsSelectedState = arguments?.getBooleanArray(ARG_DEFAULT_SELECTED_INDEXES) ?:
                booleanArrayOf()
        return AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMultiChoiceItems(items, defaultItemsSelectedState) { dialog, which, isChecked ->
            defaultItemsSelectedState[which] = isChecked
            listener.onItemClick(dialog, which, isChecked)
        }
        .setPositiveButton(confirmText) { dialog, which ->
            dialog.dismiss()
            listener.onConfirmSelection(dialog, defaultItemsSelectedState)
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
                    "Listener must implement MultipleSelectionDialogFragment.Listener")
        }

    }

    companion object {

        fun newInstance(title: String, items: Array<String>,
                        confirmText: String, defaultItemsSelectedState: BooleanArray):
                MultipleSelectionDialogFragment =
            MultipleSelectionDialogFragment().apply {
                arguments = bundleOf(
                        ARG_TITLE to title,
                        ARG_ITEMS to items,
                        ARG_CONFIRM_TEXT to confirmText,
                        ARG_DEFAULT_SELECTED_INDEXES to defaultItemsSelectedState
                )
            }

    }

}