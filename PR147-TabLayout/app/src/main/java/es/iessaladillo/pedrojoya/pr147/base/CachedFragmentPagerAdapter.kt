package es.iessaladillo.pedrojoya.pr147.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import android.view.ViewGroup

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
