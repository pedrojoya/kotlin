package es.iessaladillo.pedrojoya.pr169.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr169.R
import es.iessaladillo.pedrojoya.pr169.base.Event
import es.iessaladillo.pedrojoya.pr169.base.RequestState
import es.iessaladillo.pedrojoya.pr169.data.models.TranslateResponse
import es.iessaladillo.pedrojoya.pr169.data.remote.ApiService
import es.iessaladillo.pedrojoya.pr169.extensions.doOnImeAction
import es.iessaladillo.pedrojoya.pr169.extensions.hideSoftKeyboard
import es.iessaladillo.pedrojoya.pr169.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(ApiService.getInstance(applicationContext).api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        observeTranslation()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        txtWord.doOnImeAction(EditorInfo.IME_ACTION_SEARCH) { translate() }
        txtWord.doAfterTextChanged {
            if (txtTranslation.text.isNotBlank()) {
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
        hideSoftKeyboard()
        resetViews()
        if (txtWord.text.isNotBlank()) {
            viewModel.translateFromApi(txtWord.text.toString().trim())
        }
    }

    private fun resetViews() {
        txtTranslation.setText("")
        txtTranslation.isFocusable = false
    }

    private fun observeTranslation() {
        viewModel.translation.observe(this, Observer { request ->
            when (request) {
                is RequestState.Loading -> pbTranslating.visibility = View.VISIBLE
                is RequestState.Error -> showRequestError(request.exception)
                is RequestState.Result<TranslateResponse> -> showTranslation(request.data)
            }
        })
    }

    private fun showTranslation(response: TranslateResponse) {
        pbTranslating.visibility = View.INVISIBLE
        if (response.code == HttpURLConnection.HTTP_OK) {
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
