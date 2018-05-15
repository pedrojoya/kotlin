package es.iessaladillo.pedrojoya.pr014

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.lucasurbas.listitemview.ListItemView
import es.iessaladillo.pedrojoya.pr014.components.MessageManager
import es.iessaladillo.pedrojoya.pr014.components.ToastMessageManager
import es.iessaladillo.pedrojoya.pr014.data.Repository
import es.iessaladillo.pedrojoya.pr014.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr014.data.Student
import es.iessaladillo.pedrojoya.pr014.utils.inflate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val STATE_LIST_STATE = "STATE_LIST_STATE"
    private val STATE_DATA = "STATE_DATA"

    private val messageManager: MessageManager by lazy { ToastMessageManager() }
    private val repository: Repository by lazy { RepositoryImpl }
    private lateinit var data: ArrayList<Student>
    private var listState: Parcelable? = null
    private lateinit var adapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreSavedInstanceState(savedInstanceState)
        initViews()
        // First load.
        if (savedInstanceState == null) {
            loadStudents()
        }
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        data = savedInstanceState?.getParcelableArrayList(STATE_DATA) ?:
                ArrayList<Student>()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        listState = lstStudents.onSaveInstanceState()
        outState?.putParcelable(STATE_LIST_STATE, listState)
        outState?.putParcelableArrayList(STATE_DATA, adapter.data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        listState = savedInstanceState?.getParcelable(STATE_LIST_STATE)
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            lstStudents.onRestoreInstanceState(listState)
        }
    }

    private fun initViews() {
        lstStudents.emptyView = lblEmptyView
        adapter = MainActivityAdapter(this, data,
                { _, _, position -> deleteStudent(position) })
        lstStudents.adapter = adapter
        lstStudents.setOnItemClickListener { _, _, position, _ ->
            showStudent(lstStudents.getItemAtPosition(position) as Student) }
    }

    private fun showStudent(student: Student) {
        messageManager.showMessage(lstStudents, getString(R.string
                .main_activity_student_selected, student.name))
    }

    private fun deleteStudent(position: Int) {
        val student = lstStudents.getItemAtPosition(position) as Student
        repository.deleteStudent(position)
        adapter.remove(student)
        messageManager.showMessage(lstStudents, getString(R.string
                .main_activity_student_deleted, student.name))
    }

    private fun loadStudents() {
        adapter.clear()
        adapter.addAll(repository.getStudents())
    }
}

class MainActivityAdapter(
        context: Context,
        val data: ArrayList<Student>,
        val callback: (MenuItem, Student, Int) -> Unit) :
        ArrayAdapter<Student>(context, R.layout.activity_main_item, data) {

    override fun getCount() = data.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView ?: parent!!.inflate(R.layout
                .activity_main_item)
        val viewHolder = itemView.tag as? ViewHolder ?: onCreateViewHolder(itemView)
        viewHolder.bind(data[position], position)
        return itemView
    }

    private fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    inner class ViewHolder(itemView: View) {

        val itemView: ListItemView = itemView as ListItemView

        fun bind(student: Student, position: Int) {
            with(itemView) {
                title = student.name
                subtitle = "${student.grade} ${student.level}"
                iconColor = if (student.age < ADULT_AGE) {
                    ContextCompat.getColor(
                            context, R.color.accent)
                } else {
                    ContextCompat.getColor(
                            context, R.color.primary)
                }
                setOnMenuItemClickListener {
                    callback(it, student, position)
                }
            }
        }

    }

}
