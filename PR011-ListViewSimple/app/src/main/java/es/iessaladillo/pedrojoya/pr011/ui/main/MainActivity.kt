package es.iessaladillo.pedrojoya.pr011.ui.main

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr011.R
import es.iessaladillo.pedrojoya.pr011.data.local.Database
import es.iessaladillo.pedrojoya.pr011.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr011.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    private val listAdapter by lazy {
        ArrayAdapter(this, android.R.layout.simple_list_item_1, viewModel.data)
    }

    private val isValidForm
        get() = txtName.isNotBlank()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnAdd.setOnClickListener { addStudent() }
        txtName.apply {
            afterTextChanged { checkIsValidForm() }
            onImeAction {
                if (isValidForm) {
                    addStudent()
                }
            }
        }
        lstStudents.apply {
            setOnItemClickListener { _, _, position, _ ->
                listAdapter.getItem(position)?.let { showStudent(it) }
            }
            setOnItemLongClickListener { _, _, position, _ ->
                deleteStudent(position)
                true
            }
            adapter = listAdapter
            emptyView = lblEmptyView
        }
        // Initial state
        checkIsValidForm()
    }

    private fun addStudent() {
        hideKeyboard()
        viewModel.addStudent(txtName.text.toString())
        // ArrayAdapter always scroll to bottom on notifyDataSetChanged.
        listAdapter.notifyDataSetChanged()
        txtName.setText("")
        checkIsValidForm()
    }

    private fun deleteStudent(position: Int) {
        viewModel.deleteStudent(position)
        // ArrayAdapter always scroll to bottom on notifyDataSetChanged.
        listAdapter.notifyDataSetChanged()
    }

    private fun showStudent(student: String) {
        Toast.makeText(this, student, Toast.LENGTH_SHORT).show()
    }

    private fun checkIsValidForm() {
        btnAdd.isEnabled = isValidForm
    }

}
