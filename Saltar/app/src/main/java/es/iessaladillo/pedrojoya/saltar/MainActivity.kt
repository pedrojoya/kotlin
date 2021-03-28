package es.iessaladillo.pedrojoya.saltar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarVistas()


    }

    private fun configurarVistas() {
        btnConvertir.setOnClickListener { _ -> calcular() }
        etCantidad.doAfterTextChanged {
            resetarResultado()
            if (etCantidad.text.isBlank()) {
                etCantidad.setText("0")
                etCantidad.selectAll()
            }
        }


    }

    private fun calcular() {
        val cantidad = etCantidad.text.toString().toIntOrNull() ?: 0
        val resultado = when (rgOpciones.checkedRadioButtonId) {
            R.id.radioS -> sumar(cantidad)
            else -> restar(cantidad)
        }
        tvResultado.text = resultado
    }

    private fun restar(cantidad: Int): String {
        val resultado = cantidad - 1
        return resultado.toString()
    }

    private fun sumar(cantidad: Int): String {
        val resultado: Int = cantidad + 10
        return resultado.toString()
    }

    private fun resetarResultado() {
        tvResultado.text = ""
    }

}




