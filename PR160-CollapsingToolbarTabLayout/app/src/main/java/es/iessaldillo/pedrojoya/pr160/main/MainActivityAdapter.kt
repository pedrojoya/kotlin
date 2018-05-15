package es.iessaldillo.pedrojoya.pr160.main

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import es.iessaldillo.pedrojoya.pr160.R
import es.iessaldillo.pedrojoya.pr160.base.CachedFragmentPagerAdapter

private const val NUMBER_OF_PAGES = 3

internal class MainActivityAdapter(fm: FragmentManager, private val mContext: Context) :
        CachedFragmentPagerAdapter(fm) {

    private val pageHeaders = intArrayOf(R.drawable.photo0, R.drawable.photo1, R.drawable.photo2)

    override fun getItem(position: Int): Fragment = PageFragment.newInstance(position + 1)

    override fun getCount(): Int = NUMBER_OF_PAGES

    override fun getPageTitle(position: Int): CharSequence? =
            mContext.getString(R.string.main_activity_tabTitle, position)

    @DrawableRes
    fun getPageHeader(position: Int): Int = pageHeaders[position]

}
