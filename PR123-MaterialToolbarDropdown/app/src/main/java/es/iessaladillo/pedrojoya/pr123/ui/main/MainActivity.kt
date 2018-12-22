package es.iessaladillo.pedrojoya.pr123.ui.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr123.R
import es.iessaladillo.pedrojoya.pr123.base.ToolbarSpinnerAdapter
import es.iessaladillo.pedrojoya.pr123.ui.info.InfoFragment
import es.iessaladillo.pedrojoya.pr123.ui.photo.PhotoFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val PHOTO_OPTION_POSITION = 0
private const val INFO_OPTION_POSITION = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            // Action bar can't show title because spinner is shown instead.
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            val adapter = ToolbarSpinnerAdapter(
                    supportActionBar!!.themedContext,
                    ArrayList(Arrays.asList(*resources.getStringArray(R.array.options))))
            spnOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int,
                                            id: Long) {
                    when (position) {
                        PHOTO_OPTION_POSITION -> showPhotoFragment()
                        INFO_OPTION_POSITION -> showInfoFragment()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
            spnOptions.adapter = adapter
        }
    }

    private fun showPhotoFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent,
                    PhotoFragment.newInstance(), PhotoFragment::class.java.simpleName)
        }
    }

    private fun showInfoFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent,
                    InfoFragment.newInstance(), InfoFragment::class.java.simpleName)
        }
    }

}

