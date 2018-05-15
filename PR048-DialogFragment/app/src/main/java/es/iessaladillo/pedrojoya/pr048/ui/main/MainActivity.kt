package es.iessaladillo.pedrojoya.pr048.ui.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr048.R
import es.iessaladillo.pedrojoya.pr048.base.*
import es.iessaladillo.pedrojoya.pr048.data.Student
import es.iessaladillo.pedrojoya.pr048.extensions.getStringArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnDateSetListener, OnTimeSetListener,
        YesNoDialogFragment.Listener, DirectSelectionDialogFragment.Listener,
        SimpleSelectionDialogFragment.Listener, MultipleSelectionDialogFragment.Listener,
        StudentsDialogFragment.Callback, CustomLayoutDialogFragment.Listener {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnDatePicker.setOnClickListener { showDatePickerDialog() }
        btnTimePicker.setOnClickListener { showTimePickerDialog() }
        btnYesNoAlert.setOnClickListener { showConfirmDeletionDialog() }
        btnDirectSelectionAlert.setOnClickListener { showDirectSelectionDialog() }
        btnSimpleSelectionAlert.setOnClickListener { showSimpleSelectionDialog() }
        btnMultipleSelectionAlert.setOnClickListener { showMultipleSelectionDialog() }
        btnCustomLayoutAlert.setOnClickListener { showCustomLayoutDialog() }
        btnAdapterAlert.setOnClickListener { showAdapterDialog() }
    }

    private fun showDatePickerDialog() {
        DatePickerDialogFragment.newInstance()
                .show(supportFragmentManager, "DatePickerDialogFragment")
    }

    private fun showTimePickerDialog() {
        TimePickerDialogFragment.newInstance()
                .show(supportFragmentManager, "TimePickerDialogFragment")
    }

    private fun showConfirmDeletionDialog() {
        YesNoDialogFragment.newInstance(getString(R.string.confirm_dialog_title), getString(R.string.confirm_dialog_message),
                getString(R.string.confirm_dialog_yes), getString(R.string.confirm_dialog_no))
                .show(supportFragmentManager, "ConfirmDeletionDialogFragment")
    }

    private fun showDirectSelectionDialog() {
        DirectSelectionDialogFragment.newInstance(getString(R.string.direct_selection_dialog_shift), getStringArray(R.array.shifts))
                .show(supportFragmentManager, "DirectSelectionDialogFragment")
    }

    private fun showSimpleSelectionDialog() {
        SimpleSelectionDialogFragment.newInstance(getString(R.string.simple_selection_dialog_shift),
                getStringArray(R.array.shifts),
                getString(R.string.simple_selection_dialog_positiveButton), 0)
                .show(supportFragmentManager, "SimpleSelectionDialogFragment")
    }

    private fun showMultipleSelectionDialog() {
        MultipleSelectionDialogFragment.newInstance(
                getString(R.string.multiple_selection_dialog_shift), getStringArray(R.array.shifts),
                getString(R.string.multiple_selection_dialog_positiveButton),
                booleanArrayOf(true, false, false))
                .show(this.supportFragmentManager, "MultipleSelectionDialogFragment")
    }

    private fun showCustomLayoutDialog() {
        CustomLayoutDialogFragment().show(supportFragmentManager, "CustomLayoutDialogFragment")
    }

    private fun showAdapterDialog() {
        StudentsDialogFragment().show(supportFragmentManager, "StudentsDialogFragment")
    }

    @SuppressLint("DefaultLocale")
    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        toast(getString(R.string.main_activity_selected,
                String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1)
                        + "/" + String.format("%04d", year)))
    }

    @SuppressLint("DefaultLocale")
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        toast(getString(R.string.main_activity_selected,
                String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)))
    }

    // Positive button on YesNoDialog.
    override fun onPositiveButtonClick(dialog: DialogInterface) {
        toast(getString(R.string.main_activity_user_deleted))
    }

    // Negative button on YesNoDialog.
    override fun onNegativeButtonClick(dialog: DialogInterface) {
        toast(getString(R.string.main_activity_no_delete))
    }

    // Selection on DirectSelectionDialog. Receives the index of the selected option.
    override fun onItemSelected(dialog: DialogFragment, which: Int) {
        toast(getString(R.string.main_activity_selected, getStringArray(R.array.shifts)[which]))
    }

    private fun buildShiftsMessage(optionIsChecked: BooleanArray, shifts: Array<String>): String {
        var result = shifts
                .filterIndexed { index, _ -> optionIsChecked[index] }
                .joinToString(", ")
        if (result.isBlank())
            result = getString(R.string.multiple_selection_dialog_no_shift_selected)
        return result
    }

    // Positive button on SimpleSelectionDialog. Receives the index of the selected option.
    override fun onConfirmSelection(dialog: DialogInterface, which: Int) {
        toast(getString(R.string.main_activity_selected, getStringArray(R.array.shifts)[which]))
    }

    // Item selected on SimpleSelectionDialog. Receives the index of the selected
    // option.
    override fun onItemSelected(dialog: DialogInterface, which: Int) {
        // Nothing done.
    }

    // Positive button on MultipleSelectionDialog. Receives the selection state of each option.
    override fun onConfirmSelection(dialog: DialogInterface, itemsSelectedState: BooleanArray) {
        toast(getString(R.string.multiple_selection_dialog_selected,
                buildShiftsMessage(itemsSelectedState, getStringArray(R.array.shifts))))
    }

    // Item clicked on MultipleSelectionDialog. Receives the index of the option clicked and the
    // state it is.
    override fun onItemClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        // Nothing done.
    }

    // Click on item in adapter. Receives selected student.
    override fun onListItemClick(dialog: DialogFragment, student: Student) {
        toast(getString(R.string.main_activity_selected, student.name))
    }

    // Positive button on custom layout dialog.
    override fun onLoginClick(dialog: DialogFragment) {
        toast(R.string.main_activity_login)
    }

    // Neutral button on custom layout dialog.
    override fun onCancelClick(dialog: DialogFragment) {
        // Nothing done.
    }

}