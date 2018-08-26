package es.iessaladillo.pedrojoya.pr016

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import es.iessaladillo.pedrojoya.pr016.base.StardardExpandableListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val ADULT_AGE = 18

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy { createAdapter() }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        lstStudents.apply {
            setAdapter(mAdapter)
            // All groups initially expanded.
            for (i in 0 until mAdapter.groupCount) {
                expandGroup(i)
            }
            setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                // Use getExpandableListAdapter() instead of getAdapter() in
                // case you need the adapter.
                val (name, _, level, grade) = mAdapter.getChild(groupPosition, childPosition)
                Toast.makeText(this@MainActivity, getString(R.string.main_activity_student_info, name, grade,
                        level), Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    private fun createAdapter(): StudentsAdapter {
        val groups = arrayListOf(
                "CFGM Sistemas Microinformáticos y Redes",
                "CFGS Desarrollo de Aplicaciones Multiplataforma"
        )
        val children = arrayListOf(
                arrayListOf(
                        Student("Baldomero", 16, "CFGM", "2º"),
                        Student("Sergio", 27, "CFGM", "1º"),
                        Student("Atanasio", 17, "CFGM", "1º"),
                        Student("Oswaldo", 26, "CFGM", "1º"),
                        Student("Rodrigo", 22, "CFGM", "2º"),
                        Student("Antonio", 16, "CFGM", "1º")
                ),
                arrayListOf(
                        Student("Pedro", 22, "CFGS", "2º"),
                        Student("Pablo", 22, "CFGS", "2º"),
                        Student("Rodolfo", 21, "CFGS", "1º"),
                        Student("Gervasio", 24, "CFGS", "2º"),
                        Student("Prudencia", 20, "CFGS", "2º"),
                        Student("Gumersindo", 17, "CFGS", "2º"),
                        Student("Gerardo", 18, "CFGS", "1º"),
                        Student("Óscar", 21, "CFGS", "2º")
                )
        )
        return StudentsAdapter(groups, children)
    }

}

private class StudentsAdapter(
        private val groups: ArrayList<String>,
        private val children: ArrayList<ArrayList<Student>>)
    : StardardExpandableListAdapter<String, Student, GroupViewHolder, ChildViewHolder>(
        groups, children, R.layout.activity_main_group, R.layout.activity_main_child) {

    override fun onCreateChildViewHolder(itemView: View) = ChildViewHolder(itemView)

    override fun onBindChildViewHolder(viewHolder: ChildViewHolder, groupPosition: Int, childPosition: Int) {
        viewHolder.bind(children[groupPosition][childPosition])
    }

    override fun onBindGroupViewHolder(viewHolder: GroupViewHolder, groupPosition: Int, expanded: Boolean) {
        viewHolder.bind(groups[groupPosition], getChildrenCount(groupPosition), expanded)
    }

    override fun onCreateGroupViewHolder(itemView: View) = GroupViewHolder(itemView)

}

private class ChildViewHolder(itemView: View) {

    private val lblName: TextView by lazy {
        ViewCompat.requireViewById<TextView>(itemView, R.id.lblName)
    }
    private val lblGrade: TextView by lazy {
        ViewCompat.requireViewById<TextView>(itemView, R.id.lblGrade)
    }

    internal fun bind(student: Student) {
        lblName.text = student.name
        lblGrade.text = student.grade
        lblName.setTextColor(
                if (student.age < ADULT_AGE) ContextCompat.getColor(lblName.context, R.color.primary_400)
                else ContextCompat.getColor(lblName.context, R.color.primary_text)
        )
    }
}

private class GroupViewHolder(itemView: View) {

    private val lblLevelHeader: TextView by lazy {
        ViewCompat.requireViewById<TextView>(itemView, R.id.lblLevelHeader)
    }
    private val imgIndicator: ImageView by lazy {
        ViewCompat.requireViewById<ImageView>(itemView, R.id.imgIndicator)
    }
    private val llColumnsHeader: LinearLayout by lazy {
        ViewCompat.requireViewById<LinearLayout>(itemView, R.id.llColumnsHeader)
    }

    internal fun bind(group: String, childrenCount: Int, isExpanded: Boolean) {
        lblLevelHeader.text = group
        // Show or hide columns header and change indicator.
        if (childrenCount == 0) {
            imgIndicator.visibility = View.INVISIBLE
            llColumnsHeader.visibility = View.GONE
        } else {
            imgIndicator.visibility = View.VISIBLE
            if (isExpanded) {
                imgIndicator.setImageResource(R.drawable.ic_expand_less_white_24dp)
                llColumnsHeader.visibility = View.VISIBLE
            } else {
                imgIndicator.setImageResource(R.drawable.ic_expand_more_white_24dp)
                llColumnsHeader.visibility = View.GONE
            }
        }
    }

}

