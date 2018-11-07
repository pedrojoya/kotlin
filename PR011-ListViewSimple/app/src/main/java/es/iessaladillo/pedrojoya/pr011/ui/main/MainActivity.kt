package es.iessaladillo.pedrojoya.pr011.ui.main

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr011.R
import es.iessaladillo.pedrojoya.pr011.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr011.data.local.Database
import es.iessaladillo.pedrojoya.pr011.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    private val listAdapter by lazy {
        ArrayAdapter(this, android.R.layout.simple_list_item_1, viewModel.data)
    }

    private fun isValidForm() = txtName.text.isNotBlank()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        checkInitialState()

    }

    private fun setupViews() {
        btnAdd.setOnClickListener { addStudent() }
        txtName.apply {
            setAfterTextChangedListener { checkIsValidForm() }
            setOnImeActionDone { addStudent() }
        }
        lstStudents.apply {
            setOnItemClickListener { _, _, position, _ ->
                listAdapter.getItem(position)?.let { showStudent(it) }
            }
            setOnItemLongClickListener { _, _, position, _ ->
                listAdapter.getItem(position)?.let { deleteStudent(it) }
                true
            }
            emptyView = lblEmptyView
            adapter = listAdapter
        }
    }

    private fun addStudent() {
        if (isValidForm()) {
            hideSoftKeyboard()
            viewModel.addStudent(txtName.text.toString())
            // ArrayAdapter always scroll to bottom on notifyDataSetChanged.
            listAdapter.notifyDataSetChanged()
            resetForm()
        }
    }

    private fun resetForm() {
        txtName.setText("")
    }

    private fun checkInitialState() {
        checkIsValidForm()
    }

    private fun deleteStudent(student: String) {
        hideSoftKeyboard()
        viewModel.deleteStudent(student)
        // ArrayAdapter always scroll to bottom on notifyDataSetChanged.
        listAdapter.notifyDataSetChanged()
    }

    private fun showStudent(student: String) {
        hideSoftKeyboard()
        toast(student)
    }

    private fun checkIsValidForm() {
        btnAdd.isEnabled = isValidForm()
    }

}
