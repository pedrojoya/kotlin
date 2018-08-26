package es.iessaladillo.pedrojoya.pr048.ui.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
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
        Toast.makeText(this, getString(R.string.main_activity_selected,
                String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1)
                        + "/" + String.format("%04d", year)), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("DefaultLocale")
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        Toast.makeText(this, getString(R.string.main_activity_selected,
                String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)), Toast.LENGTH_SHORT).show()
    }

    // Positive button on YesNoDialog.
    override fun onPositiveButtonClick(dialog: DialogInterface) {
        Toast.makeText(this, getString(R.string.main_activity_user_deleted), Toast.LENGTH_SHORT).show()
    }

    // Negative button on YesNoDialog.
    override fun onNegativeButtonClick(dialog: DialogInterface) {
        Toast.makeText(this, getString(R.string.main_activity_no_delete), Toast.LENGTH_SHORT).show()
    }

    // Selection on DirectSelectionDialog. Receives the index of the selected option.
    override fun onItemSelected(dialog: DialogFragment, which: Int) {
        Toast.makeText(this, getString(R.string.main_activity_selected, getStringArray(R.array.shifts)[which]), Toast.LENGTH_SHORT).show()
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
        Toast.makeText(this, getString(R.string.main_activity_selected, getStringArray(R.array.shifts)[which]), Toast.LENGTH_SHORT).show()
    }

    // Item selected on SimpleSelectionDialog. Receives the index of the selected
    // option.
    override fun onItemSelected(dialog: DialogInterface, which: Int) {
        // Nothing done.
    }

    // Positive button on MultipleSelectionDialog. Receives the selection state of each option.
    override fun onConfirmSelection(dialog: DialogInterface, itemsSelectedState: BooleanArray) {
        Toast.makeText(this, getString(R.string.multiple_selection_dialog_selected,
                buildShiftsMessage(itemsSelectedState, getStringArray(R.array.shifts))), Toast.LENGTH_SHORT).show()
    }

    // Item clicked on MultipleSelectionDialog. Receives the index of the option clicked and the
    // state it is.
    override fun onItemClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        // Nothing done.
    }

    // Click on item in adapter. Receives selected student.
    override fun onListItemClick(dialog: DialogFragment, student: Student) {
        Toast.makeText(this, getString(R.string.main_activity_selected, student.name), Toast.LENGTH_SHORT).show()
    }

    // Positive button on custom layout dialog.
    override fun onLoginClick(dialog: DialogFragment) {
        Toast.makeText(this, R.string.main_activity_login, Toast.LENGTH_SHORT).show()
    }

    // Neutral button on custom layout dialog.
    override fun onCancelClick(dialog: DialogFragment) {
        // Nothing done.
    }

}