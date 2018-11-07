package es.iessaladillo.pedrojoya.pr021.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr021.R
import es.iessaladillo.pedrojoya.pr021.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr021.data.local.Database
import es.iessaladillo.pedrojoya.pr021.data.local.model.Country
import es.iessaladillo.pedrojoya.pr021.extensions.onItemSelected
import es.iessaladillo.pedrojoya.pr021.extensions.toast
import es.iessaladillo.pedrojoya.pr021.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        btnShow.setOnClickListener {
            if (spnCountry.selectedItemPosition != 0) {
                showCountry(spnCountry.selectedItem as Country)
            }
        }
        spnCountry.apply {
            val data = listOf(Country(R.drawable.no_flag,
                    getString(R.string.main_activity_choose_one_country)), *viewModel.data.toTypedArray())
            adapter = MainActivityAdapter(data)
            onItemSelected { _, _, _, _ -> checkIsValidForm() }
        }
    }

    private fun checkIsValidForm() {
        btnShow.isEnabled = spnCountry.selectedItemPosition != 0
    }

    private fun showCountry(country: Country) {
        toast(country.name)
    }

}


