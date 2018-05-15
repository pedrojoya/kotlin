@file:JvmName("ViewPagerExt")
package es.iessaldillo.pedrojoya.pr160.extensions

import android.support.v4.view.ViewPager

fun ViewPager.onPageSelected(action: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float,
                                    positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            action(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}

    })
}

