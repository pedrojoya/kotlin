package es.iessaladillo.pedrojoya.kotlinrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.kotlinrecyclerview.AlumnosAdapter.ViewHolder
import es.iessaladillo.pedrojoya.kotlinrecyclerview.model.Alumno
import es.iessaladillo.pedrojoya.kotlinrecyclerview.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_item.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    val STATE_LISTA = "STATE_LISTA"

    lateinit var mAdapter: AlumnosAdapter
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    var estadoLista: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVistas(savedInstanceState)
    }

    private fun initVistas(savedInstanceState: Bundle?) {
        lblEmpty.visible(true)
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = AlumnosAdapter()
        mAdapter.onClickListener = { alumno: Alumno, position: Int -> toast("Alumno $position: ${alumno.nombre}") }
        with(lstAlumos) {
            hasFixedSize()
            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
        lstAlumos.adapter = mAdapter
        if (savedInstanceState == null) {
            loadData()
        }
    }

    private fun loadData() {
        doAsync {
            Thread.sleep(3000)
            val datos = mutableListOf(Alumno("Baldomero", 18), Alumno("Germán Ginés", 15))
            uiThread {
                mAdapter.data = datos
                lblEmpty.visible(mAdapter.data.isEmpty())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        estadoLista = mLayoutManager.onSaveInstanceState()
        outState?.putParcelable(STATE_LISTA, estadoLista)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        estadoLista = savedInstanceState?.getParcelable(STATE_LISTA)
    }

    override fun onResume() {
        super.onResume()
        mLayoutManager.onRestoreInstanceState(estadoLista)
    }

}

class AlumnosAdapter : RecyclerView.Adapter<ViewHolder>() {

    var data: MutableList<Alumno> = mutableListOf()
        set(value) {
            data.clear()
            data.addAll(value)
            notifyDataSetChanged()
        }

    var onClickListener = { alumno: Alumno, i: Int -> Unit }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_main_item, parent, false)
        val viewHolder = ViewHolder(itemView)
        itemView.setOnClickListener({
            onClickListener(data[viewHolder.adapterPosition], viewHolder.adapterPosition)
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        // Necesario para que no se obtenga en cada bind.
        val nombreView = itemView?.lblNombre
        val edadView = itemView?.lblEdad

        fun bind(alumno: Alumno) {
            nombreView?.text = alumno.nombre
            edadView?.text = alumno.edad.toString()
        }

    }

}

