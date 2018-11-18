package es.iessaladillo.pedrojoya.pr086.ui.main;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr086.data.Repository;
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student;

@SuppressWarnings("unused")
class MainFragmentViewModel extends ViewModel {

    final LiveData<List<Student>> students;
    private final Repository repository;

    MainFragmentViewModel(Repository repository) {
        this.repository = repository;
        students = repository.queryStudents();
    }

    void addStudent(Student student) {
        repository.insertStudent(student);
    }

    void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }

}
