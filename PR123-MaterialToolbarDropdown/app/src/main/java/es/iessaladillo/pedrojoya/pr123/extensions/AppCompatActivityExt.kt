@file:JvmName("AppCompatActivityExt")

package es.iessaladillo.pedrojoya.pr123.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.addFragment(
        @IdRes parentResId: Int,
        fragment: Fragment,
        tag: String = fragment.javaClass.simpleName,
        toBackStack: Boolean = false,
        backstackTag: String = tag,
        transition: Int = 0) {
    supportFragmentManager?.addFragment(parentResId, fragment, tag, toBackStack, backstackTag, transition)
}

inline fun AppCompatActivity.inTransaction(action: FragmentTransaction.() -> Unit) {
    with (supportFragmentManager.beginTransaction()) {
        action()
        commit()
    }
}


fun AppCompatActivity.findFragmentByTag(tag: String): Fragment? =
        supportFragmentManager?.findFragmentByTag(tag)

