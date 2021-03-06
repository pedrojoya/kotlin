package es.iessaladillo.pedrojoya.pr048.ui.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.pr048.R

class CustomLayoutDialogFragment : DialogFragment() {

    private lateinit var listener: Listener

    interface Listener {
        fun onLoginClick(dialog: DialogFragment)

        fun onCancelClick(dialog: DialogFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
        .setTitle(R.string.custom_layout_dialog_title)
        .setView(LayoutInflater.from(requireContext()).inflate(R.layout.dialog_login, null))
        .setPositiveButton(R.string.custom_layout_dialog_positiveButton) { _, _ ->
            listener.onLoginClick(this@CustomLayoutDialogFragment)
        }
        .setNegativeButton(R.string.custom_layout_dialog_negativeButton) { _, _ ->
            listener.onCancelClick(this@CustomLayoutDialogFragment)
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
                    "Listener must implement CustomLayoutDialogFragment.Listener")
        }

    }

}
