@file:JvmName("TabLayoutExt")
package es.iessaladillo.pedrojoya.pr105.extensions

import android.support.design.widget.TabLayout

inline fun TabLayout.forEachIndexed(action: (index: Int, tab: TabLayout.Tab?) -> Unit) {
    for (index in 0 until tabCount) {
        action(index, getTabAt(index))
    }
}

operator fun TabLayout.iterator() = object : MutableIterator<TabLayout.Tab> {
    private var index = 0
    override fun hasNext() = index < tabCount
    override fun next() = getTabAt(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() = removeTabAt(--index)
}

inline fun TabLayout.onTabSelected(crossinline action: (TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            action(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {}

        override fun onTabReselected(tab: TabLayout.Tab) {}
    })
}
