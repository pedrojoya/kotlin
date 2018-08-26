package es.iessaladillo.pedrojoya.pr239.data.remote

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import java.util.*

private const val STOCK_LOOKUP = 1

class StockManager {

    interface Listener {
        fun onPriceChanged(price: Int)
    }

    private val clients = ArrayList<Listener>()

    private var running: Boolean = false
    private var workerThread: HandlerThread = HandlerThread("stockmanagerworker")

    private val handler: Handler by lazy {
        // Create handler for the worker thread.
        object : Handler(workerThread.looper) {
            override fun handleMessage(msg: Message) {
                if (msg.what == STOCK_LOOKUP) {
                    updatePriceFromServer()
                    for (listener in clients) {
                        listener.onPriceChanged(price)
                    }
                    // Enqueue update in 1 second.
                    sendEmptyMessageDelayed(STOCK_LOOKUP, 1000)
                }
            }
        }

    }
    private val random = Random()

    private var price: Int = 0

    private fun setUp() {
        workerThread = HandlerThread("stockmanagerworker")
        workerThread.start()
    }

    private fun updatePriceFromServer() {
        // We simulate.
        price += random.nextInt(10)
    }

    fun addListener(listener: Listener) {
        if (!running) {
            setUp()
        }
        clients.add(listener)
        handler.sendEmptyMessage(STOCK_LOOKUP)
    }

    fun removeListener(listener: Listener) {
        clients.remove(listener)
        handler.postDelayed({
            if (clients.isEmpty()) {
                stopAllWork()
            }
        }, 5000)
    }

    private fun stopAllWork() {
        workerThread.quitSafely()
        running = false
    }

}
