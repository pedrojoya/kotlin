package es.iessaladillo.pedrojoya.pr105.ui.main.option2


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.base.OnFragmentShownListener
import es.iessaladillo.pedrojoya.pr105.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.pr105.extensions.forEachIndexed
import es.iessaladillo.pedrojoya.pr105.extensions.onTabSelected
import es.iessaladillo.pedrojoya.pr105.ui.main.option2.tab1.Option2Tab1Fragment
import es.iessaladillo.pedrojoya.pr105.ui.main.option2.tab2.Option2Tab2Fragment
import kotlinx.android.synthetic.main.fragment_option2.*


class Option2Fragment : Fragment() {

    private lateinit var onToolbarAvailableListener: OnToolbarAvailableListener
    private lateinit var onFragmentShownListener: OnFragmentShownListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_option2, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        toolbar.title = getString(R.string.activity_main_option2)
        onToolbarAvailableListener.onToolbarAvailable(toolbar)
    }

    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        try {
            onToolbarAvailableListener = activity as OnToolbarAvailableListener
        } catch (e: Exception) {
            throw ClassCastException(activity.toString() + " must implement OnToolbarAvailableListener")
        }
        try {
            onFragmentShownListener = activity as OnFragmentShownListener
        } catch (e: Exception) {
            throw ClassCastException(activity.toString() + " must implement OnFragmentShownListener")
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

    companion object {

        fun newInstance() = Option2Fragment()

    }


}


