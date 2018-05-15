package es.iessaladillo.pedrojoya.pr048.base

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import androidx.core.os.bundleOf
import java.util.*

private const val ARG_HOURS = "ARG_HOURS"
private const val ARG_MINUTES = "ARG_MINUTES"
private const val ARG_IES_24_HOURS = "ARG_IES_24_HOURS"

class TimePickerDialogFragment : DialogFragment() {

    private lateinit var listener: TimePickerDialog.OnTimeSetListener
    private val calendar by lazy { Calendar.getInstance() }
    private val hours by lazy {
        arguments?.getInt(ARG_HOURS, calendar.get(Calendar.HOUR_OF_DAY)) ?:
        calendar.get(Calendar.HOUR_OF_DAY)
    }
    private val minutes by lazy {
        arguments?.getInt(ARG_MINUTES, calendar.get(Calendar.MINUTE)) ?:
        calendar.get(Calendar.MINUTE)
    }
    private val is24Hour by lazy {
        arguments?.getBoolean(ARG_MINUTES, true) ?: true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            TimePickerDialog(requireActivity(), listener, hours, minutes, is24Hour)

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        listener = try {
            if (targetFragment != null) {
                targetFragment as OnTimeSetListener
            } else {
                activity as OnTimeSetListener
            }
        } catch (e: ClassCastException) {
            throw ClassCastException(
                    "Listener must implement TimePickerDialog.OnTimeSetListener")
        }

    }

    companion object {

        @Suppress("unused")
        fun newInstance(hours: Int, minutes: Int, is24Hour: Boolean): DatePickerDialogFragment =
            DatePickerDialogFragment().apply {
                arguments = bundleOf(
                        ARG_HOURS to hours,
                        ARG_MINUTES to minutes,
                        ARG_IES_24_HOURS to is24Hour
                )
            }

        fun newInstance(): TimePickerDialogFragment {
            return TimePickerDialogFragment()
        }

    }

}