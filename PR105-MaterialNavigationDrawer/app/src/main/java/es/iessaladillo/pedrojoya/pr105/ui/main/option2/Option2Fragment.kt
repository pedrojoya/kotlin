package es.iessaladillo.pedrojoya.pr105.ui.main.option2


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.pr105.extensions.forEachIndexed
import es.iessaladillo.pedrojoya.pr105.extensions.onTabSelected
import kotlinx.android.synthetic.main.fragment_option2.*
import java.util.*


class Option2Fragment : Fragment() {

    private lateinit var listener: OnToolbarAvailableListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_option2, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        listener.onToolbarAvailable(toolbar, getString(R.string.activity_main_option2))
    }

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            listener = activity as OnToolbarAvailableListener
        } catch (e: Exception) {
            throw ClassCastException(activity!!.toString() + " debe implementar la interfaz OnToolbarAvailableListener")
        }

    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(Option2Tab1Fragment(), getString(R.string.option2_fragment_students), R.drawable.ic_face_white_24dp)
            addFragment(Option2Tab2Fragment(), getString(R.string.option2_fragment_data), R.drawable.ic_message_white_24dp)
        }
        viewPager.adapter = adapter
        tabLayout.run {
            setupWithViewPager(viewPager)
            forEachIndexed {index, tab -> tab?.setIcon(adapter.getPageIcon(index)) }
            onTabSelected { tab: TabLayout.Tab -> if (tab.position == 0) fab.show() else fab.hide() }
        }
    }

}

internal class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

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

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = "  " + titles[position]

    @DrawableRes
    fun getPageIcon(position: Int): Int = icons[position]

}

