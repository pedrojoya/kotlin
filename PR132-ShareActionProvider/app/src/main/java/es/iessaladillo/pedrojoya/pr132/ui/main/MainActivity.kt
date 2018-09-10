package es.iessaladillo.pedrojoya.pr132.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import es.iessaladillo.pedrojoya.pr132.R
import es.iessaladillo.pedrojoya.pr132.extensions.getStringArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_list_item_1,
                arrayListOf(*getStringArray(R.array.students)))
    }
    private var shareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        lstStudents.run {
            emptyView = lblEmpty
            adapter = listAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        shareActionProvider = (MenuItemCompat.getActionProvider(
                menu.findItem(R.id.mnuShare)) as ShareActionProvider).apply {
            setShareIntent(createShareIntent())
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun createShareIntent() = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, getStringArray(R.array.students).joinToString("\n"))
    }

}
