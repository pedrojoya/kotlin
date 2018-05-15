package es.iessaladillo.pedrojoya.pr011

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr011.data.Database
import es.iessaladillo.pedrojoya.pr011.data.Repository
import es.iessaladillo.pedrojoya.pr011.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr011.extensions.afterTextChanged
import es.iessaladillo.pedrojoya.pr011.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr011.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr011.extensions.onActionDone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var mAdapter: ArrayAdapter<String>

    private val isValidForm
        get() = txtName.isNotBlank()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        btnAdd.setOnClickListener { addStudent() }
        with (txtName) {
            afterTextChanged { checkIsValidForm() }
            onActionDone {
                if (isValidForm) {
                    addStudent()
                }
            }
        }
        with (lstStudents) {
            setOnItemClickListener { _, _, position, _ -> showStudent(mAdapter.getItem(position)) }
            setOnItemLongClickListener { _, _, position, _ ->
                deleteStudent(position)
                true
            }
            mAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, viewModel.data)
            adapter = mAdapter
            emptyView = lblEmptyView
        }
        // Initial state
        checkIsValidForm()
    }

    private fun addStudent() {
        viewModel.addStudent(txtName.text.toString())
        // ArrayAdapter always scroll to bottom on notifyDataSetChanged.
        mAdapter.notifyDataSetChanged()
        txtName.setText("")
        checkIsValidForm()
    }

    private fun deleteStudent(position: Int) {
        viewModel.deleteStudent(position)
        // ArrayAdapter always scroll to bottom on notifyDataSetChanged.
        mAdapter.notifyDataSetChanged()
    }

    private fun showStudent(student: String) {
        toast(student)
    }

    private fun checkIsValidForm() {
        btnAdd.isEnabled = isValidForm
    }

}

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    val data by lazy { repository.queryStudents() }

    fun addStudent(student: String) {
        repository.addStudent(student)
    }

    fun deleteStudent(position: Int) {
        repository.deleteStudent(position)
    }

}

