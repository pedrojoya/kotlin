package es.iessaladillo.pedrojoya.pr134.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr134.R
import es.iessaladillo.pedrojoya.pr134.base.BatteryStatus
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeBatteryStatus()
    }

    private fun observeBatteryStatus() {
        viewModel.batteryStatusLiveData.observe(viewLifecycleOwner, Observer { showBatteryStatus(it) })
    }

    private fun showBatteryStatus(batteryStatus: BatteryStatus) {
        val healthMessage: String = when {
            batteryStatus.hasColdHealth() -> getString(R.string.main_health_cold)
            batteryStatus.hasDeadHealth() -> getString(R.string.main_health_dead)
            batteryStatus.hasGoodHealth() -> getString(R.string.main_health_good)
            batteryStatus.hasOverheatHealth() -> getString(R.string.main_health_overheat)
            batteryStatus.hasOverVoltageHealth() -> getString(R.string.main_health_over_voltage)
            batteryStatus.hasUnspecifiedFailureHealth() -> getString(R.string.main_health_unspecified_failure)
            else -> getString(R.string.main_health_unknown)
        }
        val sb = StringBuilder()
        if (batteryStatus.isLoading) {
            sb.append(getString(R.string.main_loading)).append(" ")
            if (batteryStatus.isPluggedToUsb) {
                sb.append(getString(R.string.main_plugged_usb)).append(" ")
            } else if (batteryStatus.isPluggedToAc) {
                sb.append(getString(R.string.main_plugged_ac)).append(" ")
            }
        } else {
            sb.append(getString(R.string.main_lblLevel)).append(" ")
        }
        sb.append("(").append(batteryStatus.level).append("%)").append(" ").append(
                healthMessage)
        lblLevel.text = sb.toString()
        pbLevel.progress = batteryStatus.level
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
