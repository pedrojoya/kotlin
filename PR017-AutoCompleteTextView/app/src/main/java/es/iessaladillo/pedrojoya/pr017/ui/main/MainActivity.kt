package es.iessaladillo.pedrojoya.pr017.ui.main

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import es.iessaladillo.pedrojoya.pr017.R
import es.iessaladillo.pedrojoya.pr017.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr017.data.local.Database
import es.iessaladillo.pedrojoya.pr017.extensions.doOnImeAction
import es.iessaladillo.pedrojoya.pr017.extensions.hideSoftKeyboard
import es.iessaladillo.pedrojoya.pr017.extensions.onPageFinished
import es.iessaladillo.pedrojoya.pr017.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val BASE_URL = "http://www.wordreference.com/es/translation.asp?tranword="

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        checkInitialState()
    }

    private fun checkInitialState() {
        checkIsValidForm()
        if (viewModel.loadedWord.isNotBlank()) {
            searchWord()
        }
    }

    private fun setupViews() {
        txtWord.apply {
            setAdapter(MainActivityAdapter(viewModel.data))
            setText(viewModel.loadedWord)
            doAfterTextChanged { checkIsValidForm() }
            doOnImeAction(EditorInfo.IME_ACTION_SEARCH) { searchWord() }
        }
        wvWeb.onPageFinished { _, _ -> wvWeb.visibility = View.VISIBLE }
        btnTranslate.setOnClickListener { searchWord() }
    }

    private fun searchWord() {
        val word = txtWord.text.toString().trim()
        if (isValidForm()) {
            hideSoftKeyboard()
            viewModel.loadedWord = word
            wvWeb.loadUrl(BASE_URL + word)
        }
    }

    private fun checkIsValidForm() {
        btnTranslate.isEnabled = isValidForm()
    }

    private fun isValidForm() = txtWord.text.isNotBlank()

}



