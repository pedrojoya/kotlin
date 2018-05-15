package es.iessaldillo.pedrojoya.pr191.utils

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.util.AttributeSet
import android.view.View

@Suppress("unused")
class BottomNavigationBehavior(context: Context, attrs: AttributeSet) :
        CoordinatorLayout.Behavior<BottomNavigationView>(context, attrs) {

    private var isSnackbarShowing = false
    private var snackbar: Snackbar.SnackbarLayout? = null

    override fun layoutDependsOn(parent: CoordinatorLayout?,
                                 child: BottomNavigationView?,
                                 dependency: View?): Boolean =
        dependency is AppBarLayout || dependency is Snackbar.SnackbarLayout


    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                                     child: BottomNavigationView,
                                     directTargetChild: View,
                                     target: View,
                                     nestedScrollAxes: Int,
                                     type: Int): Boolean = true

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout,
                                   child: BottomNavigationView,
                                   target: View,
                                   dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (isSnackbarShowing) {
            snackbar?.let { updateSnackbarPaddingByBottomNavigationView(child) }
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: BottomNavigationView?,
                                        dependency: View?): Boolean {
        if (dependency is AppBarLayout) {
            val bottom = dependency.bottom.toFloat()
            val height = dependency.height.toFloat()
            val hidingRate = (height - bottom) / height
            child!!.translationY = child.height * hidingRate
            return true
        }
        if (dependency is Snackbar.SnackbarLayout) {
            if (isSnackbarShowing) return true
            isSnackbarShowing = true
            snackbar = dependency
            updateSnackbarPaddingByBottomNavigationView(child)
            return true
        }
        return false
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout?, child: BottomNavigationView?,
                                        dependency: View?) {
        if (dependency is Snackbar.SnackbarLayout) {
            isSnackbarShowing = false
            snackbar = null
        }
        super.onDependentViewRemoved(parent, child, dependency)
    }

    private fun updateSnackbarPaddingByBottomNavigationView(view: BottomNavigationView?) {
        snackbar?.let {
            val bottomTranslate = (view!!.height - view.translationY).toInt()
            it.setPadding(it.paddingLeft, it.paddingTop, it.paddingRight, bottomTranslate)
            it.requestLayout()
        }
    }

}