@file:JvmName("FragmentManagerExt")

package es.iessaladillo.pedrojoya.pr234.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(action: FragmentTransaction.() -> Unit) {
    beginTransaction().run {
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

