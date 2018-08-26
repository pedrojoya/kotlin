package es.iessaladillo.pedrojoya.pr012.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr012.R
import es.iessaladillo.pedrojoya.pr012.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr012.data.Database
import es.iessaladillo.pedrojoya.pr012.data.Repository
import es.iessaladillo.pedrojoya.pr012.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr012.data.Student
import es.iessaladillo.pedrojoya.pr012.extensions.getQuantityString
import es.iessaladillo.pedrojoya.pr012.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr012.extensions.toast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        with (lstStudents) {
            emptyView = lblEmpty
            adapter = MainActivityAdapter(viewModel.data)
        }
    }

}

private class MainActivityViewModel(private val repository: Repository) :
ViewModel() {

    internal val data by lazy { repository.queryStudents() }

}

private class MainActivityAdapter(data: List<Student>)
    : AdapterViewBaseAdapter<Student, MainActivityAdapter.ViewHolder>(data, R.layout.activity_main_item) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    internal class ViewHolder(override val containerView: View): LayoutContainer {

        fun bind(student: Student) {
            with (student) {
                imgPhoto.setImageResource(photo)
                lblName.text = name
                lblGrade.text = lblGrade.context
                        .getString(R.string.main_activity_grade_and_level, grade, level)
                lblAge.text = lblAge.context.getQuantityString(R.plurals.main_activity_age, age, age)
                lblAge.setTextColor(if (age < 18)
                    ContextCompat.getColor(lblAge.context, R.color.accent)
                else
                    ContextCompat.getColor(lblAge.context, R.color.primary_text))
                lblRepeater.visibility = if (isRepeater) View.VISIBLE else View.INVISIBLE
                btnCall.setOnClickListener {
                    it.toast(it.context.getString(R.string.main_activity_call_sb, name))
                }
                btnMarks.setOnClickListener {
                    it.toast(it.context.getString(R.string.main_activity_show_sb_marks, name))
                }
            }
        }

    }

}

