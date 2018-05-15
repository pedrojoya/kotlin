@file:JvmName("ViewPropertyAnimatorExt")
package es.iessaladillo.pedrojoya.pr013.extensions

import android.animation.Animator
import android.view.ViewPropertyAnimator

inline fun ViewPropertyAnimator.onAnimationEnd(crossinline action: (Animator) -> Unit) {
    setListener(object : Animator.AnimatorListener {

        override fun onAnimationStart(animation: Animator) {}

        override fun onAnimationRepeat(animation: Animator) {}

        override fun onAnimationEnd(animation: Animator) {
            action(animation)
        }

        override fun onAnimationCancel(animation: Animator) {}
    })
}

