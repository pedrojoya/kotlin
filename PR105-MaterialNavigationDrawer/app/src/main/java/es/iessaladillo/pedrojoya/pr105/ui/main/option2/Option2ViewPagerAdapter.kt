package es.iessaladillo.pedrojoya.pr105.ui.main.option2

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()
    private val icons = ArrayList<Int>()

    fun addFragment(fragment: Fragment, title: String, @DrawableRes resIdIcon: Int): ViewPagerAdapter {
        fragments.add(fragment)
        titles.add(title)
        icons.add(resIdIcon)
        return this
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    operator fun get(position: Int) = getItem(position)

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = "  " + titles[position]

    @DrawableRes
    fun getPageIcon(position: Int): Int = icons[position]

}
