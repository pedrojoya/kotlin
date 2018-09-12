package es.iessaladillo.pedrojoya.pr210.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@SuppressWarnings("WeakerAccess")
public class DetailActivityViewModel extends DetailFragmentBaseViewModel {

    private final MutableLiveData<String> currentItem = new MutableLiveData<>();

    @Override
    public LiveData<String> getCurrentItem() {
        return currentItem;
    }

    @Override
    public void setCurrentItem(String item) {
        currentItem.setValue(item);
    }

}
