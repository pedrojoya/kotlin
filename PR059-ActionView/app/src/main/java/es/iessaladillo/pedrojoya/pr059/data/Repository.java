package es.iessaladillo.pedrojoya.pr059.data;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {

    @SuppressWarnings("UnusedReturnValue")
    LiveData<List<String>> queryStudents(String criteria);

}
