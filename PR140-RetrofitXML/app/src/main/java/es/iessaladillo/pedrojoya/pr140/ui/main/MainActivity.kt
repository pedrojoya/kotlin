package es.iessaladillo.pedrojoya.pr140.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter
import es.iessaladillo.pedrojoya.pr140.R
import es.iessaladillo.pedrojoya.pr140.base.Event
import es.iessaladillo.pedrojoya.pr140.base.RequestState
import es.iessaladillo.pedrojoya.pr140.base.ToolbarSpinnerAdapter
import es.iessaladillo.pedrojoya.pr140.data.model.City
import es.iessaladillo.pedrojoya.pr140.data.remote.ApiService
import es.iessaladillo.pedrojoya.pr140.data.remote.model.Counting
import es.iessaladillo.pedrojoya.pr140.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(ApiService.getInstance(applicationContext).api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observeCounting()
    }

    private fun initViews() {
        setupToolbar()
        setupChart()
        setupSpinner()
    }

    private fun observeCounting() {
        viewModel.counting.observe(this, Observer { request ->
            @Suppress("UNCHECKED_CAST")
            when (request) {
                is RequestState.Loading -> showLoading(request.isLoading)
                is RequestState.Error -> showErrorLoadingCounting(request.exception)
                is RequestState.Result<*> -> showCounting((request as RequestState.Result<Counting>).data)
            }
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupChart() {
        chart.apply {
            description = Description().apply { text = getString(R.string.main_activity_concejales) }
            setNoDataText(getString(R.string.grafico_sin_datos))
            //chart.setDescriptionPosition(0, 0);
            //chart.setDrawSliceText(true);
            isDrawHoleEnabled = true
            setHoleColor(ContextCompat.getColor(this@MainActivity, android.R.color.transparent))
            holeRadius = 26f
            transparentCircleRadius = 28f
            setDrawCenterText(true)
            centerText = getString(R.string.main_activity_elecciones)
            rotationAngle = 0f
            isRotationEnabled = true
            legend.run {
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                orientation = Legend.LegendOrientation.VERTICAL
                setDrawInside(false)
                xEntrySpace = 7f
                yEntrySpace = 5f
            }
        }
    }

    private fun setupSpinner() {
        spnToolbar.run {
            adapter = ToolbarSpinnerAdapter(supportActionBar!!.themedContext, viewModel.cities)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (position != viewModel.selectedCity) {
                        viewModel.selectedCity = position
                        viewModel.loadEscrutinio((adapterView.getItemAtPosition(position) as City).code)
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
        }
    }

    private fun showCounting(counting: Counting) {
        progressBar.visibility = View.INVISIBLE
        val countingValues = ArrayList<PieEntry>()
        val countingColors = ArrayList<Int>()
        counting.results?.parties?.filter { it.elected?.toInt()?:0 > 0 }?.forEach {
            countingColors.add(it.color)
            countingValues.add(PieEntry(it.elected?.toFloat()?:0.0f, it.name))
        }
        val dataSet = PieDataSet(countingValues, "Nº CONCEJALES").apply {
            sliceSpace = 2f // Espacio entre porciones
            selectionShift = 25f // Cuanto sobresale porción al pulsar.
            colors = countingColors
        }
        /* Opcionalmente se pueden añadir colecciones de colores.:
            VORDIPLOM_COLORS, JOYFUL_COLORS, LIBERTY_COLORS, PASTEL_COLORS,
            getHoloBlue()
           ArrayList<Integer> colors = new ArrayList<>();
           for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);
         */
        val countingData = PieData(dataSet).apply {
            setValueFormatter(LargeValueFormatter()) // Format. valores
            setValueTextSize(14f)
            setValueTextColor(Color.WHITE)
        }
        chart.run {
            data = countingData
            highlightValues(null) // Ninguno seleccionado
            // Se repinta y anima el gráfico
            invalidate()
            animateY(1500, Easing.EasingOption.EaseInOutQuad)
        }
    }

    private fun showLoading(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    }

    private fun showErrorLoadingCounting(event: Event<Exception>) {
        progressBar.visibility = View.INVISIBLE
        val exception = event.getContentIfNotHandled()
        if (exception != null) {
            Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
        }
    }

}
