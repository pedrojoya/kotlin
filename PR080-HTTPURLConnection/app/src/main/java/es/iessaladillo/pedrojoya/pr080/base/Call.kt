package es.iessaladillo.pedrojoya.pr080.base

import android.annotation.SuppressLint
import android.os.AsyncTask

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

abstract class Call<T> protected constructor() : LiveData<T>() {

    @SuppressLint("StaticFieldLeak")
    private val asyncTask = object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            doAsync()
            return null
        }

        override fun onCancelled() {
            doOnCancelled()
        }
    }

    val isCancelled: Boolean
        get() = asyncTask.isCancelled

    val isWorking: Boolean
        get() = asyncTask.status == AsyncTask.Status.RUNNING

    init {
        asyncTask.execute()
    }

    @WorkerThread
    protected abstract fun doAsync()

    @MainThread
    protected open fun doOnCancelled() { }

    fun cancel(mayInterruptIfRunning: Boolean) {
        asyncTask.cancel(mayInterruptIfRunning)
    }

}
