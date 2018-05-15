package es.iessaladillo.pedrojoya.pr011

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import es.iessaladillo.pedrojoya.pr011.base.AppCompatLifecycleActivity
import es.iessaladillo.pedrojoya.pr011.components.MessageManager
import es.iessaladillo.pedrojoya.pr011.components.ProgressBarProgressManager
import es.iessaladillo.pedrojoya.pr011.components.ProgressManager
import es.iessaladillo.pedrojoya.pr011.components.ToastMessageManager
import es.iessaladillo.pedrojoya.pr011.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr011.utils.afterTextChanged
import es.iessaladillo.pedrojoya.pr011.utils.asString
import es.iessaladillo.pedrojoya.pr011.utils.tint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatLifecycleActivity() {

    private val STATE_DATA = "STATE_DATA"

    private lateinit var data: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private val progressManager: ProgressManager by lazy { ProgressBarProgressManager(pbLoading) }
    private val messageManager: MessageManager by lazy { ToastMessageManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreSavedInstanceState(savedInstanceState)
        initViews()
        if (savedInstanceState == null) {
            loadStudents()
        }
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        // lstStudents.onRestoreInstantceState does't work well with ArrayAdapter. That's why we
        // don't save list state.
        data = savedInstanceState?.getStringArrayList(STATE_DATA) ?: ArrayList()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArrayList(STATE_DATA, data)
    }

    private fun initViews() {
        btnAdd.setOnClickListener { addStudent() }
        txtName.afterTextChanged { checkIsValidForm() }
        txtName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isValidForm()) {
                    addStudent()
                }
                true
            }
            false
        }
        lstStudents.emptyView = lblEmptyView
        lstStudents.setOnItemClickListener { _, _, position, _ -> showStudent(adapter.getItem(position)) }
        lstStudents.setOnItemLongClickListener { _, _, position, _ ->
            deleteStudent(position)
            true
        }
        adapter = ArrayAdapter<String>(this, android.R.layout
                .simple_list_item_1, data)
        lstStudents.adapter = adapter
        // Initial state
        btnAdd.tint(R.color.btn_color)
        checkIsValidForm()
    }

    private fun isValidForm() = txtName.asString().isNotEmpty()

    private fun addStudent() {
        RepositoryImpl.addStudent(txtName.asString())
        adapter.add(txtName.asString())
        txtName.setText("")
        checkIsValidForm()
    }

    private fun checkIsValidForm() {
        btnAdd.isEnabled = isValidForm()
    }

    private fun showStudent(student: String) {
        messageManager.showMessage(lstStudents, student)
    }

    private fun deleteStudent(position: Int) {
        RepositoryImpl.deleteStudent(position)
        // ArrayAdapter always scroll to bottom on remove.
        adapter.remove(adapter.getItem(position))
    }

    // In future, would be asynchronous
    private fun loadStudents() {
        progressManager.showIndeterminateProgress()
        adapter.clear()
        adapter.addAll(RepositoryImpl.getStudents())
        progressManager.hideIndeterminateProgress()
    }

}
