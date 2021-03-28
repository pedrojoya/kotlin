package es.iessaladillo.pedrojoya.pr244.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr244.R
import es.iessaladillo.pedrojoya.pr244.base.dataBinding
import es.iessaladillo.pedrojoya.pr244.base.observeEvent
import es.iessaladillo.pedrojoya.pr244.databinding.ActivityMainBinding
import es.iessaladillo.pedrojoya.pr244.utils.hideSoftKeyboard

class MainActivity : AppCompatActivity() {

//    private val binding: ActivityMainBinding by lazy {
//        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
//            setLifecycleOwner { this@MainActivity }
//        }
//    }

    private val binding: ActivityMainBinding by dataBinding(R.layout.activity_main)
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.viewMessage.observeEvent(this) {
            binding.root.hideSoftKeyboard()
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}
