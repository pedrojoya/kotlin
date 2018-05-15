@file:JvmName("FragmentManagerExt")

package es.iessaladillo.pedrojoya.pr152.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

inline fun FragmentManager.inTransaction(action: FragmentTransaction.() -> Unit) {
    with (beginTransaction()) {
        action()
        commit()
    }
}

fun FragmentManager.replaceFragment(
        @IdRes parentResId: Int,
        fragment: Fragment,
        tag: String = fragment.javaClass.simpleName,
        toBackStack: Boolean = false,
        backstackTag: String = tag,
        transition: Int = 0) {

    inTransaction {
        replace(parentResId, fragment, tag)
        if (transition != 0) {
            setTransition(transition)
        }
        if (toBackStack) {
            addToBackStack(backstackTag)
        }
    }

}
