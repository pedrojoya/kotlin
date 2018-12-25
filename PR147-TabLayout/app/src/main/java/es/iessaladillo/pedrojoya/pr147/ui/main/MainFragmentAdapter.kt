package es.iessaladillo.pedrojoya.pr147.ui.main

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import es.iessaladillo.pedrojoya.pr147.R
import es.iessaladillo.pedrojoya.pr147.ui.likes.LikesFragment
import es.iessaladillo.pedrojoya.pr147.ui.lorem.LoremFragment

private const val FRAGMENT_COUNT = 2

class MainFragmentAdapter(fm: FragmentManager, private val context: Context) :
        FragmentPagerAdapter(fm) {

    private val mTitleResIds = intArrayOf(R.string.lorem_title, R.string
            .likes_title)
    private val mIconResIds = intArrayOf(R.drawable.ic_share_white_24dp,
            R.drawable.ic_thumb_up_white_24dp)

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> LoremFragment.newInstance()
                1 -> LikesFragment.newInstance()
                else -> throw RuntimeException("Unknown adapter position")
            }

    override fun getCount(): Int = FRAGMENT_COUNT

    override fun getPageTitle(position: Int): CharSequence? =
            context.getString(mTitleResIds[position])

    @DrawableRes
    fun getPageIcon(position: Int): Int = mIconResIds[position]

}
