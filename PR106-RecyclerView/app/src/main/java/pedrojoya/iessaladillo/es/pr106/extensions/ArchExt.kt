@file:JvmName("ArchExt")

package pedrojoya.iessaladillo.es.pr106.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.FragmentActivity

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline creator: () -> T): T {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return creator() as T
        }
    }).get(T::class.java)
}