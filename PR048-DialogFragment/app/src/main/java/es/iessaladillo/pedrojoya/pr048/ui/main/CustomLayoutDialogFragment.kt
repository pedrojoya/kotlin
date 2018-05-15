package es.iessaladillo.pedrojoya.pr048.ui.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater

import es.iessaladillo.pedrojoya.pr048.R

class CustomLayoutDialogFragment : DialogFragment() {

    private lateinit var listener: Listener

    interface Listener {
        fun onLoginClick(dialog: DialogFragment)

        fun onCancelClick(dialog: DialogFragment)
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
