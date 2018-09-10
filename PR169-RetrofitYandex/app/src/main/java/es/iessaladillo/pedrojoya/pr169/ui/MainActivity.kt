package es.iessaladillo.pedrojoya.pr169.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr169.R
import es.iessaladillo.pedrojoya.pr169.base.Event
import es.iessaladillo.pedrojoya.pr169.base.RequestState
import es.iessaladillo.pedrojoya.pr169.data.models.TranslateResponse
import es.iessaladillo.pedrojoya.pr169.data.remote.ApiService
import es.iessaladillo.pedrojoya.pr169.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(ApiService.getInstance(applicationContext).api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeTranslation()
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupFab()
        txtWord.onAction(EditorInfo.IME_ACTION_SEARCH) { translate() }
        txtWord.afterTextChanged {
            if (txtTranslation.isNotBlank()) {
                txtTranslation.setText("")
            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupFab() {
        fab.setOnClickListener { translate() }
    }

    private fun translate() {
        hideKeyboard()
        resetViews()
        if (txtWord.isNotBlank()) {
            viewModel.translateFromApi(txtWord.text.toString().trim())
        }
    }

    private fun resetViews() {
        txtTranslation.setText("")
        txtTranslation.isFocusable = false
    }

    private fun observeTranslation() {
        viewModel.translation.observe(this, Observer { request ->
            @Suppress("UNCHECKED_CAST")
            when (request) {
                is RequestState.Loading -> pbTranslating.visibility = if (request.isLoading) View.VISIBLE else View.INVISIBLE
                is RequestState.Error -> showRequestError(request.exception)
                is RequestState.Result<*> -> showTranslation((request as RequestState.Result<TranslateResponse>).data)
            }
        })
    }

    private fun showTranslation(response: TranslateResponse) {
        pbTranslating.visibility = View.INVISIBLE
        if (response.code == 200) {
            txtTranslation.setText(response.text.joinToString(", "))
        } else {
            showTranslationError()
        }
    }

    private fun showRequestError(event: Event<Exception>) {
        pbTranslating.visibility = View.INVISIBLE
        val exception = event.getContentIfNotHandled()
        if (exception != null) {
            Snackbar.make(txtTranslation!!,
                    getString(R.string.main_activity_request_error, exception.message),
                    Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showTranslationError() {
        Snackbar.make(txtTranslation, R.string.main_activity_translation_error,
                Snackbar.LENGTH_SHORT).show()
    }

}
