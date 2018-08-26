package es.iessaladillo.pedrojoya.pr021.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr021.R
import es.iessaladillo.pedrojoya.pr021.base.DropDownBaseAdapter
import es.iessaladillo.pedrojoya.pr021.data.Country
import es.iessaladillo.pedrojoya.pr021.data.Database
import es.iessaladillo.pedrojoya.pr021.data.Repository
import es.iessaladillo.pedrojoya.pr021.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr021.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr021.extensions.onItemSelected
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        btnShow.setOnClickListener {
            if (spnCountry!!.selectedItemPosition != 0) {
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
        Toast.makeText(this, country.name, Toast.LENGTH_SHORT).show()
    }

}

private class MainActivityViewModel(private val repository: Repository) :
        ViewModel() {

    val data: List<Country> by lazy { repository.queryCountries() }

}

private class MainActivityAdapter(data: List<Country>) :
        DropDownBaseAdapter<Country, CollapsedViewHolder, ExpandedViewHolder>(data,
                R.layout.activity_main_item_collapsed, R.layout.activity_main_item_expanded) {

    override fun onCreateCollapsedViewHolder(itemView: View): CollapsedViewHolder {
        return CollapsedViewHolder(itemView)
    }

    override fun onBindCollapsedViewHolder(holder: CollapsedViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateExpandedViewHolder(itemView: View): ExpandedViewHolder {
        return ExpandedViewHolder(itemView)
    }

    override fun onBindExpandedViewHolder(holder: ExpandedViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

private class CollapsedViewHolder(private val itemView: View) {

    private val imgFlag by lazy { ViewCompat.requireViewById(itemView, R.id.imgFlag) as ImageView }
    private val lblName by lazy { ViewCompat.requireViewById(itemView, R.id.lblName) as TextView }

    fun bind(country: Country) {
        imgFlag.setImageResource(country.flagResId)
        lblName.text = country.name
    }

}

private class ExpandedViewHolder(private val itemView: View) {

    private val imgFlag by lazy { ViewCompat.requireViewById(itemView, R.id.imgFlag) as ImageView }
    private val lblName by lazy { ViewCompat.requireViewById(itemView, R.id.lblName) as TextView }

    fun bind(country: Country) {
        imgFlag.setImageResource(country.flagResId)
        lblName.text = country.name
    }

}
