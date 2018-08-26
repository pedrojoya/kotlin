@file:JvmName("AppCompatActivityExt")

package es.iessaladillo.pedrojoya.pr211.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(
        @IdRes parentResId: Int,
        fragment: Fragment,
        tag: String = fragment.javaClass.simpleName,
        toBackStack: Boolean = false,
        backstackTag: String = tag,
        transition: Int = 0) {
    supportFragmentManager?.replaceFragment(parentResId, fragment, tag, toBackStack, backstackTag, transition)
}

