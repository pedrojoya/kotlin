package es.iessaladillo.pedrojoya.pr086.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.data.Database
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.Student
import es.iessaladillo.pedrojoya.pr086.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityAdapter.Callback {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        lstStudents.apply {
            adapter = MainActivityAdapter(viewModel.students, this@MainActivity)
            setOnItemClickListener { _, _, position, _ ->
                showStudent(lstStudents.getItemAtPosition(position) as Student)
            }
        }
    }

    private fun showStudent(student: Student) {
        Toast.makeText(this, getString(R.string.main_activity_click_on,
                student.name), Toast.LENGTH_SHORT).show()
    }

    override fun onCall(student: Student) {
        Toast.makeText(this, getString(R.string.main_activity_call_sb, student.name), Toast.LENGTH_SHORT).show()
    }

    override fun onSendMessage(student: Student) {
        Toast.makeText(this, getString(R.string.main_activity_send_message_to, student.name), Toast.LENGTH_SHORT).show()
    }

}
