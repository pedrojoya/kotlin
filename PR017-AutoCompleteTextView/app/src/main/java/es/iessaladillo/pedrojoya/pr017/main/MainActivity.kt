package es.iessaladillo.pedrojoya.pr017.main

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import es.iessaladillo.pedrojoya.pr017.R
import es.iessaladillo.pedrojoya.pr017.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr017.data.Database
import es.iessaladillo.pedrojoya.pr017.data.Repository
import es.iessaladillo.pedrojoya.pr017.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr017.data.Word
import es.iessaladillo.pedrojoya.pr017.extensions.afterTextChanged
import es.iessaladillo.pedrojoya.pr017.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr017.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr017.extensions.onPageFinished
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_item.*

private const val BASE_URL = "http://www.wordreference.com/es/translation.asp?tranword="

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        with (txtWord) {
            setAdapter(MainActivityAdapter(viewModel.data))
            afterTextChanged { checkIsValidForm() }
        }
        wvWeb.onPageFinished { _, _ -> wvWeb.visibility = View.VISIBLE }
        btnTranslate.setOnClickListener {
            viewModel.loadedWord = txtWord.text.toString()
            searchWord(viewModel.loadedWord)
        }
        // Initial state.
        checkIsValidForm()
        if (!viewModel.loadedWord.isEmpty()) {
            searchWord(viewModel.loadedWord)
        }
    }

    private fun searchWord(word: String) {
        wvWeb.loadUrl(BASE_URL + word)
    }

    private fun checkIsValidForm() {
        btnTranslate.isEnabled = txtWord.isNotBlank()
    }

}

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    internal val data by lazy { repository.queryWords() }
    internal var loadedWord = ""

}

internal class MainActivityAdapter(words: List<Word>) :
        AdapterViewBaseAdapter<Word, ViewHolder>(words, R.layout.activity_main_item),
        Filterable {

    private val original = arrayListOf(*words.toTypedArray())

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getFilter(): Filter = object: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            data = original.filter { it.english.contains(constraint.toString(), true) }
            return Filter.FilterResults().apply {
                values = data
                count = data.size
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.count?:0 > 0) {
                notifyDataSetChanged()
            }
        }

    }

}

internal class ViewHolder(override val containerView: View): LayoutContainer {

    fun bind(word: Word) {
        imgPhoto.setImageResource(word.photoResId)
        lblEnglish.text = word.english
    }

}

