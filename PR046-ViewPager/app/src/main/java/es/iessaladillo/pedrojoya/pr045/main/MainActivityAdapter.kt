package es.iessaladillo.pedrojoya.pr045.main

import android.content.Context
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import es.iessaladillo.pedrojoya.pr045.R

private const val NUMBER_OF_PAGES = 5

internal class MainActivityAdapter(context: Context) : PagerAdapter() {

    override fun getCount(): Int = NUMBER_OF_PAGES

    override fun instantiateItem(collection: ViewGroup, position: Int): Any =
            LayoutInflater.from(collection.context)
                    .inflate(R.layout.activity_main_page, collection, false).apply {
                        setupPage(this, position)
                        collection.addView(this, 0)
                    }

    private fun setupPage(view: View, position: Int) {
        ViewCompat.requireViewById<TextView>(view, R.id.lblPage).text = position.toString()
        ViewCompat.requireViewById<Button>(view, R.id.btnShow).setOnClickListener {
            show(view, position) }
    }

    private fun show(view: View, position: Int) {
        Toast.makeText(view.context, position.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, theobject: Any): Boolean {
        // Are the same object
        return view === theobject
    }

    override fun startUpdate(arg0: ViewGroup) {}

    override fun finishUpdate(arg0: ViewGroup) {}

    override fun saveState(): Parcelable? = null

    override fun restoreState(parcelable: Parcelable?, arg1: ClassLoader?) {}

}
