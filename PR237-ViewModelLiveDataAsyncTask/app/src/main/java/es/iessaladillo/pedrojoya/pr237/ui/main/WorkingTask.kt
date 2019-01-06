package es.iessaladillo.pedrojoya.pr237.ui.main

import es.iessaladillo.pedrojoya.pr237.base.AsyncLiveTask
import es.iessaladillo.pedrojoya.pr237.base.Event
import es.iessaladillo.pedrojoya.pr237.base.Resource

class WorkingTask(private val steps: Int) : AsyncLiveTask<Resource<Event<String>>>() {

    override fun doAsync() {
        postValue(Resource.loading(0))
        var i = 0
        while (i < steps && !isCancelled) {
            try {
                work()
            } catch (e: InterruptedException) {
                return
            }

            postValue(Resource.loading(i + 1))
            i++
        }
        if (!isCancelled) {
            postValue(Resource.success(Event("Task completed successfully")))
        }
    }

    override fun doOnCancelled() {
        postValue(Resource.error(Exception("Task cancelled by user")))
    }

    @Throws(InterruptedException::class)
    private fun work() {
        Thread.sleep(1000)
    }

}
