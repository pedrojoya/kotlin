package es.iessaladillo.pedrojoya.pr048.ui.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.pr048.R
import es.iessaladillo.pedrojoya.pr048.data.local.Database
import es.iessaladillo.pedrojoya.pr048.data.local.model.Student

class StudentsDialogFragment : DialogFragment() {

    private lateinit var listener: Callback

    interface Callback {
        fun onListItemClick(dialog: DialogFragment, student: Student)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireActivity())
                    .setTitle(R.string.adapter_dialog_student)
                    .setAdapter(StudentsDialogFragmentAdapter(Database.students)) {
                        _, which ->
                            listener.onListItemClick(
                                    this@StudentsDialogFragment, Database.students[which])
                    }
                    .create()

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            listener = activity as Callback
        } catch (t: Throwable) {
            throw ClassCastException(activity!!.toString() +
                    " must implement StudentsDialogFragment.Listener")
        }

    }

}