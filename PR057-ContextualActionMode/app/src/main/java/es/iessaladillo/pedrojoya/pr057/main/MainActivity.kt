package es.iessaladillo.pedrojoya.pr057.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView.MultiChoiceModeListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr057.R
import es.iessaladillo.pedrojoya.pr057.extensions.getQuantityString
import es.iessaladillo.pedrojoya.pr057.extensions.getSelectedItems
import es.iessaladillo.pedrojoya.pr057.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr057.extensions.thenTrue
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel()
        initViews()
    }

    private fun initViews() {
        setupStudent()
        setupSubjectList()
    }

    private fun setupStudent() {
        txtStudent.setOnLongClickListener { v -> startContextualMode(v).thenTrue() }
    }

    private fun startContextualMode(v: View) {
        startSupportActionMode(object : android.support.v7.view.ActionMode.Callback {

            override fun onPrepareActionMode(mode: android.support.v7.view.ActionMode, menu:
            Menu): Boolean = false

            override fun onCreateActionMode(mode: android.support.v7.view.ActionMode, menu: Menu): Boolean =
                    mode.menuInflater.inflate(R.menu.activity_main_contextual, menu).thenTrue()

            override fun onActionItemClicked(mode: android.support.v7.view.ActionMode,
                                             item: MenuItem): Boolean =
                when (item.itemId) {
                    R.id.mnuEvaluate -> evaluateStudent().thenTrue()
                    R.id.mnuDelete -> removeStudent().thenTrue()
                    else -> false
                }

            override fun onDestroyActionMode(arg0: android.support.v7.view.ActionMode) { }

        })
        // Select view.
        v.isSelected = true
    }

    private fun removeStudent() {
        txtStudent.setText("")
    }

    private fun evaluateStudent() {
        toast(getString(R.string.main_activity_evaluate_student, txtStudent!!.text.toString()))
    }

    private fun setupSubjectList() {
        mAdapter = ArrayAdapter(this, R.layout.activity_main_item, R.id.lblSubject,
                viewModel.subjects)
        with(lstSubjects) {
            choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            adapter = mAdapter
            setMultiChoiceModeListener(object : MultiChoiceModeListener {
                override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

                override fun onDestroyActionMode(mode: ActionMode) {}

                override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean =
                    mode.menuInflater.inflate(R.menu.activity_main_contextual, menu).thenTrue()

                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean =
                    when (item.itemId) {
                        R.id.mnuEvaluate -> evaluateSubjects(lstSubjects
                                .getSelectedItems(false)).thenTrue()
                        R.id.mnuDelete -> deleteSubjects(lstSubjects
                                .getSelectedItems(true)).thenTrue()
                        else -> false
                    }

                override fun onItemCheckedStateChanged(mode: ActionMode, position: Int, id: Long,
                                                       checked: Boolean) {
                    // Update action bar title.
                    mode.title = getString(R.string.main_activity_number_of_number,
                            lstSubjects.checkedItemCount, lstSubjects.count)
                }
            })
            // Simple click also activates contextual action mode.
            setOnItemClickListener { _, _, position, _ ->
                setItemChecked(position, true)
            }
        }
    }

    private fun evaluateSubjects(subjectList: List<String>) {
        toast(getString(R.string.main_activity_evaluate_subjects,
                subjectList.joinToString(", ")))
    }

    private fun deleteSubjects(subjectList: List<String>) {
        with(subjectList) {
            forEach { mAdapter.remove(it) }
            mAdapter.notifyDataSetChanged()
            toast(getQuantityString(R.plurals.main_activity_subjects_deleted, size, size))
        }
    }

}