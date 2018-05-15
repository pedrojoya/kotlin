@file:JvmName("MenuItemExt")

package es.iessaladillo.pedrojoya.pr059.extensions

import android.view.MenuItem

inline fun MenuItem.setOnActionExpandListener(
        crossinline onMenuItemActionExpandAction: (MenuItem) -> Boolean = { false },
        crossinline onMenuItemActionCollapseAction: (MenuItem) -> Boolean = { false }) {

    setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem) = onMenuItemActionExpandAction(item)
        override fun onMenuItemActionCollapse(item: MenuItem) = onMenuItemActionCollapseAction(item)
    })

}