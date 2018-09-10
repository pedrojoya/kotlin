package es.iessaladillo.pedrojoya.pr057.ui.main

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView.MultiChoiceModeListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr057.R
import es.iessaladillo.pedrojoya.pr057.extensions.getQuantityString
import es.iessaladillo.pedrojoya.pr057.extensions.getSelectedItems
import es.iessaladillo.pedrojoya.pr057.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider()
    private val listAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, R.layout.activity_main_item, R.id.lblSubject, viewModel.subjects)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setupStudent()
        setupSubjectList()
    }

    private fun setupStudent() {
        txtStudent.setOnLongClickListener { v ->
            startContextualMode(v)
            true
        }
    }

    private fun startContextualMode(v: View) {
        startSupportActionMode(object : androidx.appcompat.view.ActionMode.Callback {

            override fun onPrepareActionMode(mode: androidx.appcompat.view.ActionMode, menu: Menu): Boolean = false

            override fun onCreateActionMode(mode: androidx.appcompat.view.ActionMode, menu: Menu): Boolean {
                mode.menuInflater.inflate(R.menu.activity_main_contextual, menu)
                return true
            }

            override fun onActionItemClicked(mode: androidx.appcompat.view.ActionMode, item: MenuItem): Boolean =
                    when (item.itemId) {
                        R.id.mnuEvaluate -> evaluateStudent().let { true }
                        R.id.mnuDelete -> removeStudent().let { true }
                        else -> false
                    }

            override fun onDestroyActionMode(mode: androidx.appcompat.view.ActionMode) {}

        })
        // Select view.
        v.isSelected = true
    }

    private fun removeStudent() {
        txtStudent.setText("")
    }

    private fun evaluateStudent() {
        Toast.makeText(this, getString(R.string.main_activity_evaluate_student, txtStudent!!.text.toString()), Toast.LENGTH_SHORT).show()
    }

    private fun setupSubjectList() {
        lstSubjects.run {
            choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            adapter = listAdapter
            setMultiChoiceModeListener(object : MultiChoiceModeListener {
                override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

                override fun onDestroyActionMode(mode: ActionMode) {}

                override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean =
                        mode.menuInflater.inflate(R.menu.activity_main_contextual, menu).let { true }

                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean =
                        when (item.itemId) {
                            R.id.mnuEvaluate -> evaluateSubjects(lstSubjects.getSelectedItems(false)).let { true }
                            R.id.mnuDelete -> deleteSubjects(lstSubjects.getSelectedItems(true)).let { true }
                            else -> false
                        }

                override fun onItemCheckedStateChanged(mode: ActionMode, position: Int, id: Long, checked: Boolean) {
                    mode.title = getString(R.string.main_activity_number_of_number,
                            lstSubjects.checkedItemCount, lstSubjects.count)
                }
            })
            // Simple click also activates contextual action mode.
            setOnItemClickListener { _, _, position, _ -> setItemChecked(position, true) }
        }
    }

    private fun evaluateSubjects(subjectList: List<String>) {
        Toast.makeText(this, getString(R.string.main_activity_evaluate_subjects,
                subjectList.joinToString(", ")), Toast.LENGTH_SHORT).show()
    }

    private fun deleteSubjects(subjectList: List<String>) {
        subjectList.apply {
            forEach { listAdapter.remove(it) }
            listAdapter.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, getQuantityString(R.plurals.main_activity_subjects_deleted, size, size), Toast.LENGTH_SHORT).show()
        }
    }

}