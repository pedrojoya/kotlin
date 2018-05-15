package es.iessaladillo.pedrojoya.pr011.base

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
open class AppCompatLifecycleActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private lateinit var registry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registry = LifecycleRegistry(this)
    }

    override fun getLifecycle() = registry

}