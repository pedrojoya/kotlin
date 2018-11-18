@file:JvmName("PopupMenuExt")

package es.iessaladillo.pedrojoya.pr086.extensions

import android.util.Log
import android.widget.PopupMenu

fun PopupMenu.enableIcons() {
    val menuHelper: Any
    val argTypes: Array<Class<Boolean>?>
    try {
        val fMenuHelper = PopupMenu::class.java.getDeclaredField("mPopup")
        fMenuHelper.isAccessible = true
        menuHelper = fMenuHelper.get(this)
        argTypes = arrayOf(Boolean::class.javaPrimitiveType)
        menuHelper.javaClass.getDeclaredMethod("setForceShowIcon", *argTypes).invoke(menuHelper, true)
    } catch (e: Exception) {
        Log.e("PopupMenu", "Error enabling icons in popup menu")
    }
}

