package es.iessaladillo.pedrojoya.pr048.base

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.core.os.bundleOf
import java.util.*

private const val ARG_YEAR = "ARG_YEAR"
private const val ARG_MONTH = "ARG_MONTH"
private const val ARG_DAY = "ARG_DAY"

class DatePickerDialogFragment : DialogFragment() {

    private lateinit var listener: DatePickerDialog.OnDateSetListener
    private val calendar by lazy { Calendar.getInstance() }
    private val year by lazy {
        arguments?.getInt(ARG_YEAR, calendar.get(Calendar.YEAR)) ?:
        calendar.get(Calendar.YEAR)
    }
    private val month by lazy {
        arguments?.getInt(ARG_MONTH, calendar.get(Calendar.MONTH)) ?:
        calendar.get(Calendar.MONTH)
    }
    private val day by lazy {
        arguments?.getInt(ARG_DAY, calendar.get(Calendar.DAY_OF_MONTH)) ?:
        calendar.get(Calendar.DAY_OF_MONTH)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(requireActivity(), listener, year, month, day)
    }

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        listener = try {
            if (targetFragment != null) {
                targetFragment as OnDateSetListener
            } else {
                activity as OnDateSetListener
            }
        } catch (e: ClassCastException) {
            throw ClassCastException("Listener must implement DatePickerDialogFragment.OnDateSetListener")
        }

    }

    companion object {

        @Suppress("unused")
        fun newInstance(year: Int, month: Int, day: Int) =
                DatePickerDialogFragment().apply {
                    arguments = bundleOf(
                            ARG_YEAR to year,
                            ARG_MONTH to month,
                            ARG_DAY to day)
                }

        fun newInstance(): DatePickerDialogFragment {
            return DatePickerDialogFragment()
        }
    }

}