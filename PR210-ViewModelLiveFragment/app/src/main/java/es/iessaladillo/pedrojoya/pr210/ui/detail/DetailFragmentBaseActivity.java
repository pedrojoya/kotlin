package es.iessaladillo.pedrojoya.pr210.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

public abstract class DetailFragmentBaseActivity<T extends DetailFragmentBaseViewModel> extends
        AppCompatActivity {

    public abstract Class<T> getViewModelClass();

}
