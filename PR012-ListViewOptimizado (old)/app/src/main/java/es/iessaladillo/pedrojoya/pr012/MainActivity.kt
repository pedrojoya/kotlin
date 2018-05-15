package es.iessaladillo.pedrojoya.pr012

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import es.iessaladillo.pedrojoya.pr012.components.ToastMessageManager
import es.iessaladillo.pedrojoya.pr012.data.RespositoryImpl
import es.iessaladillo.pedrojoya.pr012.data.Student
import es.iessaladillo.pedrojoya.pr012.utils.inflate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_item.view.*

class MainActivity : AppCompatActivity() {

    private val repository = RespositoryImpl
    private lateinit var adapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        lstStudents.emptyView = lblEmpty
        adapter = MainActivityAdapter(this)
        lstStudents.adapter = adapter
        loadStudents()
    }

    private fun loadStudents() {
        adapter.data = repository.getStudents()
    }

}

class MainActivityAdapter(context: Context) :
        ArrayAdapter<Student>(context, R.layout.activity_main_item, mutableListOf<Student>()) {

    private val messageManager = ToastMessageManager

    var data = mutableListOf<Student>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount() = data.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView ?: parent!!.inflate(R.layout.activity_main_item)
        val viewHolder: ViewHolder = (convertView?.tag as?ViewHolder) ?: onCreateViewHolder(itemView)
        itemView.tag = viewHolder
        onBindViewHolder(viewHolder, position)
        return itemView
    }

    private fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    private fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(data[position])

    inner class ViewHolder(val itemView: View) {

        fun bind(student: Student) {
            with(itemView) {
                imgPhoto.setImageResource(student.photo)
                lblName.text = student.name
                lblGrade.text = lblGrade.context
                        .getString(R.string.main_activity_grade_and_level, student.grade, student.level)
                lblAge.text = lblAge.context
                        .resources
                        .getQuantityString(R.plurals.main_activity_age, student.age, student.age)
                lblAge.setTextColor(
                        if (student.age < 18)
                            ContextCompat.getColor(lblAge.context, R.color.accent)
                        else
                            ContextCompat.getColor(lblAge.context, R.color.primary_text))
                lblRepeater.visibility = if (student.repeater) View.VISIBLE else View.INVISIBLE
                btnCall.setOnClickListener({ view ->
                    messageManager.showMessage(view, view.context.getString(R.string.llamar_a, student.name))
                })
                btnMarks.setOnClickListener({ view ->
                    messageManager.showMessage(view, view.context.getString(R.string.ver_notas_de, student.name))
                })
            }
        }

    }

}
