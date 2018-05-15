package es.iessaladillo.pedrojoya.pr178.ui.main

import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.extensions.toast
import kotlinx.android.synthetic.main.fragment_bottomsheet.*
import es.iessaladillo.pedrojoya.pr178.data.Student

private const val ARG_STUDENT = "ARG_STUDENT"
private const val SHEET_PEAK_HEIGHT = 650

class MenuBottomSheetDialogFragment : BottomSheetDialogFragment(),
        NavigationView.OnNavigationItemSelectedListener {

    private lateinit var student: Student

    private val mBottomSheetBehaviorCallback =
            object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN ||
                    newState == BottomSheetBehavior.STATE_SETTLING) {
                dismiss()
            }

        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_bottomsheet, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        obtainArguments()
        initViews(view!!)
    }

    private fun initViews(view: View) {
        setupBottomSheet(view)
    }

    private fun setupBottomSheet(view: View) {
        val params = (view.parent as View)
                .layoutParams as CoordinatorLayout.LayoutParams
        params.behavior.let {
            if (it is BottomSheetBehavior<*>) {
                it.setBottomSheetCallback(mBottomSheetBehaviorCallback)
                // To assure sheet is completely shown.
                it.peekHeight = SHEET_PEAK_HEIGHT//get the height dynamically
            }

        }
        setupNavigationView()
    }

    private fun setupNavigationView() {
        with (navigationView) {
            setNavigationItemSelectedListener(this@MenuBottomSheetDialogFragment)
            menu.findItem(R.id.mnuTitle).title = student.name
        }
    }

    private fun obtainArguments() {
        student = arguments!!.getParcelable<Parcelable>(ARG_STUDENT) as Student
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mnuCall -> {
                call()
                dismiss()
                true
            }
            R.id.mnuSendMessage -> {
                sendMessage()
                dismiss()
                true
            }
            R.id.mnuNotes -> {
                seeNotes()
                dismiss()
                true
            }
            else -> false
        }

    private fun call() {
        toast(getString(R.string.menubottomsheetfragment_call, student.name))
    }

    private fun sendMessage() {
        toast(getString(R.string.menubottomsheetfragment_send_message, student.name))
    }

    private fun seeNotes() {
        toast(getString(R.string.menubottomsheetfragment_see_notes, student.name))
    }

    companion object {

        internal fun newInstance(student: Student): MenuBottomSheetDialogFragment =
                MenuBottomSheetDialogFragment().apply {
                    arguments = bundleOf(ARG_STUDENT to student)
                }

    }

}
