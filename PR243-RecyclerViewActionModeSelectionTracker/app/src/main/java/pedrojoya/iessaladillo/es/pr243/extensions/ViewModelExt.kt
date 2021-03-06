@file:JvmName("ViewModelExt")

package pedrojoya.iessaladillo.es.pr243.extensions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.viewModelProvider(crossinline creator: () ->
T) =
        lazy {
            ViewModelProviders.of(this, object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return creator() as T
                }
            }).get(T::class.java)
        }
