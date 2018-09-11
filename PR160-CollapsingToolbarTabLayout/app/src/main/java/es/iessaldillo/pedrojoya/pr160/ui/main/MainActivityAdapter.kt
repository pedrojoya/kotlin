package es.iessaldillo.pedrojoya.pr160.ui.main

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import es.iessaldillo.pedrojoya.pr160.R
import es.iessaldillo.pedrojoya.pr160.base.CachedFragmentPagerAdapter

private const val NUMBER_OF_PAGES = 3

internal class MainActivityAdapter(fm: FragmentManager, private val mContext: Context) :
        CachedFragmentPagerAdapter(fm) {

    private val pageHeaders = intArrayOf(R.drawable.photo0, R.drawable.photo1, R.drawable.photo2)

    override fun getItem(position: Int): Fragment = PageFragment.newInstance()

    override fun getCount(): Int = NUMBER_OF_PAGES

    override fun getPageTitle(position: Int): CharSequence? =
            mContext.getString(R.string.main_activity_tabTitle, position)

    @DrawableRes
    fun getPageHeader(position: Int): Int = pageHeaders[position]

}
