package es.iessaldillo.pedrojoya.pr160.base

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.lang.ref.WeakReference

abstract class CachedFragmentPagerAdapter protected constructor(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {

    private val fragments = SparseArray<WeakReference<Fragment>>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        // Add fragment to SparseArray.
        fragments.put(position, WeakReference(fragment))
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, theObject: Any) {
        // Removes fragment from SparseArray.
        fragments.remove(position)
        super.destroyItem(container, position, theObject)
    }

    @Suppress("unused")
    // Return fragment in that position.
    fun getFragment(position: Int): Fragment? =
            fragments.get(position)?.get()

}
