package es.iessaladillo.pedrojoya.pr016.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr012.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr016.R
import es.iessaladillo.pedrojoya.pr016.data.local.Database
import es.iessaladillo.pedrojoya.pr016.data.local.model.Student
import es.iessaladillo.pedrojoya.pr016.extensions.toast
import es.iessaladillo.pedrojoya.pr097.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }
    private val listAdapter by lazy { createAdapter() }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        lstStudents.run {
            setAdapter(listAdapter)
            // All groups initially expanded.
            for (i in 0 until listAdapter.groupCount) {
                expandGroup(i)
            }
            setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                // Use getExpandableListAdapter() instead of getAdapter() in
                // case you need the adapter.
                val (name, _, level, grade) = listAdapter.getChild(groupPosition, childPosition)
                toast(getString(R.string.main_activity_student_info, name, grade, level))
                true
            }
        }
    }

    private fun createAdapter(): MainActivityAdapter {
        val groups = ArrayList<String>()
        val children = ArrayList<ArrayList<Student>>()
        for (pair in viewModel.students) {
            groups.add(pair.key)
            children.add(pair.value)
        }
        return MainActivityAdapter(groups, children)
    }

}


