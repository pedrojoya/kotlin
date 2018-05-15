package es.iessaladillo.pedrojoya.pr015.main

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr015.R
import es.iessaladillo.pedrojoya.pr015.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr015.data.Database
import es.iessaladillo.pedrojoya.pr015.data.Repository
import es.iessaladillo.pedrojoya.pr015.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr015.data.Word
import es.iessaladillo.pedrojoya.pr015.extensions.getViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        with (grdWords) {
            adapter = MainActivityAdapter(viewModel.data)
            setOnItemClickListener { _, _, position, _ -> showWord(position) }
        }
    }

    private fun showWord(position: Int) {
        val word = grdWords.getItemAtPosition(position) as Word
        toast(getString(R.string.main_activity_translation, word.spanish, word.english))
    }

}

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    internal val data by lazy { repository.queryWords() }

}

internal class MainActivityAdapter(data: List<Word>): AdapterViewBaseAdapter<Word,
        ViewHolder>(data, R.layout.activity_main_item) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

internal class ViewHolder(override val containerView: View): LayoutContainer {

    fun bind(word: Word) {
        with (word) {
            imgPhoto.setImageResource(photoResId)
            lblEnglish.text = english
            lblSpanish.text = spanish
        }
    }

}
