package es.iessaladillo.pedrojoya.pr017.ui.main

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr017.R
import es.iessaladillo.pedrojoya.pr017.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr017.data.local.Database
import es.iessaladillo.pedrojoya.pr017.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

private const val BASE_URL = "http://www.wordreference.com/es/translation.asp?tranword="

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        txtWord.apply {
            setAdapter(MainActivityAdapter(viewModel.data))
            afterTextChanged { checkIsValidForm() }
            onImeAction(EditorInfo.IME_ACTION_SEARCH) {
                if (txtWord.isNotBlank()) {
                    searchWord(txtWord.text.toString())
                }
            }
        }
        wvWeb.onPageFinished { _, _ -> wvWeb.visibility = View.VISIBLE }
        btnTranslate.setOnClickListener {
            searchWord(txtWord.text.toString())
        }
        // Initial state.
        checkIsValidForm()
        if (viewModel.loadedWord.isNotBlank()) {
            searchWord(viewModel.loadedWord)
        }
    }

    private fun searchWord(word: String) {
        hideKeyboard()
        viewModel.loadedWord = word
        wvWeb.loadUrl(BASE_URL + word)
    }

    private fun checkIsValidForm() {
        btnTranslate.isEnabled = txtWord.isNotBlank()
    }

}



