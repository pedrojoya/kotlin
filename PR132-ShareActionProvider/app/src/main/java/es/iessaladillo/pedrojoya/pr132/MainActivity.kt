package es.iessaladillo.pedrojoya.pr132

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import es.iessaladillo.pedrojoya.pr132.extensions.getStringArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: ArrayAdapter<String>
    private var mShareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                arrayListOf(*getStringArray(R.array.students)))
        lstStudents.run {
            emptyView = lblEmpty
            adapter = mAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        mShareActionProvider = (MenuItemCompat.getActionProvider(
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
