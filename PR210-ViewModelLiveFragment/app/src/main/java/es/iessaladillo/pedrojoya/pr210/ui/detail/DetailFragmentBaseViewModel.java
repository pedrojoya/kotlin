package es.iessaladillo.pedrojoya.pr210.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public abstract class DetailFragmentBaseViewModel extends ViewModel {

    public abstract LiveData<String> getCurrentItem();

    @SuppressWarnings("unused")
    public abstract void setCurrentItem(String item);

}
