@file:JvmName("TabLayoutExt")

package es.iessaladillo.pedrojoya.pr147.extensions

import com.google.android.material.tabs.TabLayout

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
