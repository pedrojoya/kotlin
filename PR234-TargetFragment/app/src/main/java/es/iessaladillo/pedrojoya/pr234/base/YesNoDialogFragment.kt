package es.iessaladillo.pedrojoya.pr234.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_MESSAGE = "ARG_MESSAGE"
private const val ARG_YES_TEXT = "ARG_YES_TEXT"
private const val ARG_NO_TEXT = "ARG_NO_TEXT"

class YesNoDialogFragment : DialogFragment() {

    private lateinit var listener: Listener
    private val title by lazy { arguments?.getString(ARG_TITLE) ?: "" }
    private val message by lazy { arguments?.getString(ARG_MESSAGE) ?: "" }
    private val yesText by lazy { arguments?.getString(ARG_YES_TEXT) ?: "" }
    private val noText by lazy { arguments?.getString(ARG_NO_TEXT) ?: "" }

    interface Listener {
        fun onPositiveButtonClick(dialog: DialogInterface)

        fun onNegativeButtonClick(dialog: DialogInterface)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireActivity())
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(yesText) { dialog, _ ->
                        listener.onPositiveButtonClick(dialog)
                    }
                    .setNegativeButton(noText) { dialog, _ ->
                        listener.onNegativeButtonClick(dialog)
                    }
                    .create()

    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        listener = try {
            if (targetFragment != null) {
                targetFragment as Listener
            } else {
                activity as Listener
            }
        } catch (e: Throwable) {
            throw ClassCastException("Listener must implement YesNoDialogFragment.Listener")
        }

    }

    companion object {

        fun newInstance(title: String, message: String, yesText: String,
                        noText: String): YesNoDialogFragment =
                YesNoDialogFragment().apply {
                    arguments = bundleOf(
                            ARG_TITLE to title,
                            ARG_MESSAGE to message,
                            ARG_YES_TEXT to yesText,
                            ARG_NO_TEXT to noText
                    )
                }

    }

}