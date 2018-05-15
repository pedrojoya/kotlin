package es.iessaladillo.pedrojoya.pr048.ui.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import es.iessaladillo.pedrojoya.pr048.R
import es.iessaladillo.pedrojoya.pr048.data.Student
import java.util.*


class StudentsDialogFragment : DialogFragment() {

    private lateinit var listener: Callback
    private val students = createStudentList()

    interface Callback {
        fun onListItemClick(dialog: DialogFragment, student: Student)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireActivity())
                    .setTitle(R.string.adapter_dialog_student)
                    .setAdapter(StudentsDialogFragmentAdapter(students)) {
                        _, which ->
                            listener.onListItemClick(
                                    this@StudentsDialogFragment, students[which])
                    }
                    .create()

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            listener = activity as Callback
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() +
                    " must implement StudentsDialogFragment.Listener")
        }

    }

    private fun createStudentList() =
            ArrayList<Student>().apply {
                for (i in 0..4) {
                    add(Student(
                            "Student $i",
                            "c/ Address, nº $i",
                            "http://lorempixel.com/100/100/abstract/$i/"))
                }
            }

}