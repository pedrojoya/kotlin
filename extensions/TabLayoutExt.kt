@file:JvmName("TabLayoutExt")
package es.iessaladillo.pedrojoya.pr147.extensions

import android.support.design.widget.TabLayout

inline fun TabLayout.forEach(action: (tab: TabLayout.Tab?) -> Unit) {
    for (index in 0 until tabCount) {
        action(getTabAt(index))
    }
}

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
