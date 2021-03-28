package es.iessaladillo.pedrojoya.pr147.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import es.iessaladillo.pedrojoya.pr147.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun setupViewPager() {
        fab.setImageResource(R.drawable.ic_share_black_24dp)
        fab.show()
        val adapter = MainFragmentAdapter(requireFragmentManager(),
                requireContext())
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager, true)
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.setIcon(adapter.getPageIcon(i))
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    // Al hacer drag siempre hay que ocultar
                    fab.hide()
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    // Al colocarse en un página, dependiendo de la página de la que se trate
                    // se actualiza el icono del fab y se muestra.
                    if (viewPager.currentItem == 0) {
                        fab.setImageResource(R.drawable.ic_share_black_24dp)
                        fab.show()
                    } else {
                        fab.setImageResource(R.drawable.ic_thumb_up_black_24dp)
                        fab.show()
                    }
                }
            }
        })
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Al cambiar la pestaña, dependiendo de la que sea se actualiza
                // el icono del fab y se muestra.
                if (tab.position == 0) {
                    fab.setImageResource(R.drawable.ic_share_black_24dp)
                    fab.show()
                } else {
                    fab.setImageResource(R.drawable.ic_thumb_up_black_24dp)
                    fab.show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Al cambiar de pestaña siempre hay que ocultar el fab.
                fab.hide()
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
