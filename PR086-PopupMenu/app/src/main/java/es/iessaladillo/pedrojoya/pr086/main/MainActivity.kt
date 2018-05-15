package es.iessaladillo.pedrojoya.pr086.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.widget.toast
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
        with (lstStudents) {
            adapter = MainActivityAdapter(viewModel.students, this@MainActivity)
            setOnItemClickListener { _, _, position, _ ->
                toast(getString(R.string.main_activity_click_on,
                        (lstStudents!!.getItemAtPosition(position) as Student).name))
            }
        }
    }

    override fun onCall(student: Student) {
        toast(getString(R.string.main_activity_call_sb, student.name))
    }

    override fun onSendMessage(student: Student) {
        toast(getString(R.string.main_activity_send_message_to, student.name))
    }

}
