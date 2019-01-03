package es.iessaladillo.pedrojoya.pr178.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.data.local.model.Student
import es.iessaladillo.pedrojoya.pr178.extensions.extraParcelable
import kotlinx.android.synthetic.main.fragment_bottomsheet_menu.*

private const val ARG_STUDENT = "ARG_STUDENT"
private const val SHEET_PEAK_HEIGHT = 650

class MenuBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val student: Student by extraParcelable(ARG_STUDENT)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_bottomsheet_menu, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBottomSheet(requireView())
    }

    private fun setupBottomSheet(view: View) {
        val params = (view.parent as View)
                .layoutParams as CoordinatorLayout.LayoutParams
        val bottomSheetBehavior = params.behavior as BottomSheetBehavior<*>
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN || newState == BottomSheetBehavior.STATE_SETTLING) {
                    dismiss()
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

        })
        // To assure sheet is completely shown.
        bottomSheetBehavior.peekHeight = SHEET_PEAK_HEIGHT
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigationView.setNavigationItemSelectedListener { onNavItemSelected(it) }
        navigationView.menu.findItem(R.id.mnuTitle).title = student.name
    }

    private fun onNavItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuCall -> call()
            R.id.mnuSendMessage -> sendMessage()
            R.id.mnuNotes -> seeNotes()
            else -> return false
        }
        dismiss()
        return true
    }

    private fun call() {
        Toast.makeText(requireContext(),
                getString(R.string.bottomsheet_call, student.name), Toast.LENGTH_SHORT)
                .show()
    }

    private fun sendMessage() {
        Toast.makeText(requireContext(),
                getString(R.string.bottomsheet_send_message, student.name),
                Toast.LENGTH_SHORT).show()
    }

    private fun seeNotes() {
        Toast.makeText(requireContext(),
                getString(R.string.bottomsheet_see_notes, student.name),
                Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance(student: Student): MenuBottomSheetDialogFragment =
            MenuBottomSheetDialogFragment().apply {
                arguments = bundleOf(ARG_STUDENT to student)
            }

    }

}
