package es.iessaladillo.pedrojoya.pr015.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr015.R
import es.iessaladillo.pedrojoya.pr015.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr015.data.local.Database
import es.iessaladillo.pedrojoya.pr015.data.local.model.Word
import es.iessaladillo.pedrojoya.pr015.extensions.toast
import es.iessaladillo.pedrojoya.pr015.extensions.viewModelProvider
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
        grdWords.apply {
            adapter = MainActivityAdapter(viewModel.data)
            setOnItemClickListener { _, _, position, _ ->
                showWord(grdWords.getItemAtPosition(position) as Word)
            }
        }
    }

    private fun showWord(word: Word) {
        toast(getString(R.string.main_activity_translation, word.spanish, word.english))
    }

}
