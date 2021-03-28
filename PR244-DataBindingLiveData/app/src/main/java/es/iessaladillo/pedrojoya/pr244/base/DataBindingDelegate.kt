package es.iessaladillo.pedrojoya.pr244.base

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class DataBindingDelegate<out T : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int
): ReadOnlyProperty<Activity, T> {

    private var value: T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T =
            (value ?: DataBindingUtil.setContentView(thisRef, layoutResId)).also {
                value = it
            }

}

fun <T : ViewDataBinding> Activity.dataBinding(
        @LayoutRes layoutResId: Int
): DataBindingDelegate<T>  = DataBindingDelegate(layoutResId)


