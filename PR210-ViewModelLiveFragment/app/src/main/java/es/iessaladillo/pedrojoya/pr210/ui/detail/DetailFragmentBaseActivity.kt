package es.iessaladillo.pedrojoya.pr210.ui.detail

import androidx.appcompat.app.AppCompatActivity

abstract class DetailFragmentBaseActivity<T : DetailFragmentBaseViewModel> : AppCompatActivity() {

    abstract val viewModelClass: Class<T>

}
