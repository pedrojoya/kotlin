package es.iessaladillo.pedrojoya.pr212.base

// by Christophe Beyls (https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e)
// T por the type of the instance to be created as a singleton.
// A for the type of the constructor argument.
open class SingletonHolder<out T, in A>(private val creator: (A) -> T) {

    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T =
        instance ?: synchronized(this) {
            instance ?: creator(arg).also { instance = it }
        }

}