package es.iessaladillo.pedrojoya.pr048.base

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import es.iessaladillo.pedrojoya.pr048.extensions.extraBoolean
import es.iessaladillo.pedrojoya.pr048.extensions.extraInt
import java.util.*

private const val ARG_HOURS = "ARG_HOURS"
private const val ARG_MINUTES = "ARG_MINUTES"
private const val ARG_IES_24_HOURS = "ARG_IES_24_HOURS"

class TimePickerDialogFragment : DialogFragment() {

    private lateinit var listener: TimePickerDialog.OnTimeSetListener
    private val calendar by lazy { Calendar.getInstance() }
    private val hours by extraInt(ARG_HOURS, calendar.get(Calendar.HOUR_OF_DAY))
    private val minutes by extraInt(ARG_MINUTES, calendar.get(Calendar.MINUTE))
    private val is24Hour by extraBoolean(ARG_MINUTES, true)

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