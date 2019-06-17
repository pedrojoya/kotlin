package es.iessaladillo.pedrojoya.pr005.ui.calendar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr005.R
import es.iessaladillo.pedrojoya.pr005.extensions.requireIntExtra
import kotlinx.android.synthetic.main.activity_calendar.*
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class CalendarActivity : AppCompatActivity() {

    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        getIntentData(intent)
        setupViews()
        showData()
    }

    private fun getIntentData(intent: Intent) {
        day = intent.requireIntExtra(EXTRA_DAY)
        month = intent.requireIntExtra(EXTRA_MONTH)
        year = intent.requireIntExtra(EXTRA_YEAR)
    }

    private fun setupViews() {
        btnSend.setOnClickListener { send() }
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            saveDate(year, month, dayOfMonth)
        }
    }

    private fun saveDate(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
    }

    private fun showData() {
        calendarView.date = LocalDate.of(year, month, day).atStartOfDay().atZone(
                ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    private fun send() {
        setActivityResult()
        finish()
    }

    private fun setActivityResult() {
        val result = Intent()
                .putExtras(bundleOf(
                        EXTRA_DAY to day,
                        EXTRA_MONTH to month + 1,
                        EXTRA_YEAR to year
                ))
        setResult(Activity.RESULT_OK, result)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Up == Back in order not to create a new instance of MainActivity when going up.
        onBackPressed()
        return true
    }

    companion object {

        const val EXTRA_DAY = "EXTRA_DAY"
        const val EXTRA_MONTH = "EXTRA_MONTH"
        const val EXTRA_YEAR = "EXTRA_YEAR"

        fun startForResult(activity: Activity, requestCode: Int, day: Int,
                           month: Int, year: Int) {
            val intent = Intent(activity, CalendarActivity::class.java)
                    .putExtras(bundleOf(
                            EXTRA_DAY to day,
                            EXTRA_MONTH to month,
                            EXTRA_YEAR to year
                    ))
            activity.startActivityForResult(intent, requestCode)
        }

    }

}