@file:JvmName("AppCompatActivityExt")

package es.iessaladillo.pedrojoya.pr050.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(
        @IdRes parentResId: Int,
        fragment: Fragment,
        tag: String = fragment.javaClass.simpleName,
        toBackStack: Boolean = false,
        backstackTag: String = tag,
        transition: Int = 0) {
    supportFragmentManager?.replaceFragment(parentResId, fragment, tag, toBackStack, backstackTag, transition)
}

fun AppCompatActivity.findFragmentByTag(tag: String): Fragment? =
        supportFragmentManager?.findFragmentByTag(tag)
