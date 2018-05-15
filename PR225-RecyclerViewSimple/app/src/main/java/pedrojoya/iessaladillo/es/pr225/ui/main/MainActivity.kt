package pedrojoya.iessaladillo.es.pr225.ui.main

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr225.R
import pedrojoya.iessaladillo.es.pr225.data.Database
import pedrojoya.iessaladillo.es.pr225.data.Repository
import pedrojoya.iessaladillo.es.pr225.data.RepositoryImpl
import pedrojoya.iessaladillo.es.pr225.data.Student
import pedrojoya.iessaladillo.es.pr225.extensions.getViewModel
import pedrojoya.iessaladillo.es.pr225.extensions.inflate
import pedrojoya.iessaladillo.es.pr225.extensions.snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(RepositoryImpl(Database)) }
        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { snackbar(fab, R.string.activity_main_fabClicked) }
        setupToolbar()
        setupRecyclerView(private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        with(lstStudents) {
            setHasFixedSize(true)
            lblEmpty.visibility = View.INVISIBLE
            adapter = MainActivityAdapter(viewModel.data)
            layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

}

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    internal val data by lazy { repository.queryStudents() }

}

internal class MainActivityAdapter(private val data: List<Student>) :
        RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.activity_main_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

}

internal class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(student: Student) {
        with(student) {
            lblName.text = name
            lblAddress.text = address
            Picasso.with(imgAvatar.context).load(photoUrl)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .error(R.drawable.ic_person_black_24dp)
                    .into(imgAvatar)
        }
    }

}


