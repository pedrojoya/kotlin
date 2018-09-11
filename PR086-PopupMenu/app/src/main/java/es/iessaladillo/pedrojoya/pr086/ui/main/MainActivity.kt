package es.iessaladillo.pedrojoya.pr086.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.data.local.Database
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.toast
import es.iessaladillo.pedrojoya.pr086.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        lstStudents.apply {
            adapter = MainActivityAdapter(viewModel.students).apply {
                setOnCallListener { _, student, _ -> callStudent(student) }
                setOnSendMessageListener { _, student, _ -> sendMessageToStudent(student) }
            }
            setOnItemClickListener { _, _, position, _ ->
                showStudent(lstStudents.getItemAtPosition(position) as Student)
            }
        }
    }

    private fun showStudent(student: Student) {
        toast(getString(R.string.main_activity_click_on, student.name))
    }

    private fun callStudent(student: Student) {
        toast(getString(R.string.main_activity_call_sb, student.name))
    }

    private fun sendMessageToStudent(student: Student) {
        toast(getString(R.string.main_activity_send_message_to, student.name))
    }

}
