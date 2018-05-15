@file:JvmName("AppCompatActivityExt")

package es.iessaladillo.pedrojoya.pr152.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

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
