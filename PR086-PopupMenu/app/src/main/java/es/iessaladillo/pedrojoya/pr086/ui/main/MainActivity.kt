package es.iessaladillo.pedrojoya.pr086.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.local.Database
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.toast
import es.iessaladillo.pedrojoya.pr086.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter().apply {
            setOnCallListener { _, position -> callStudent(getItem(position)) }
            setOnSendMessageListener { _, position -> sendMessageToStudent(getItem(position)) }
            setOnItemClickListener { _, position -> showStudent(getItem(position)) }
            emptyView = lblEmptyView
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        listAdapter.submitList(viewModel.getStudents(false))
    }

    private fun initViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL,
                    false)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
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
