package es.iessaladillo.pedrojoya.pr123.base

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import androidx.appcompat.widget.ThemedSpinnerAdapter
import java.util.*

// Must implement ThemedSpinnerAdapterToolAdaptador and receive toolbar context
// so it can be shown with the correct theme.
class ToolbarSpinnerAdapter(context: Context, private val data: ArrayList<String>) :
        ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item, data), ThemedSpinnerAdapter {

    private val dropDownHelper: ThemedSpinnerAdapter.Helper = ThemedSpinnerAdapter.Helper(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate with helper.
        val itemView: View = convertView ?:
            dropDownHelper.dropDownViewInflater.inflate(
                    android.R.layout.simple_spinner_dropdown_item, parent, false)
        // android.R.layout.simple_spinner_dropdown_item consists of a CheckedTextView.
        (itemView as CheckedTextView).text = data[position]
        return itemView
    }

    override fun setDropDownViewTheme(theme: Resources.Theme?) {
        // Set theme in helper
        dropDownHelper.dropDownViewTheme = theme
    }

    override fun getDropDownViewTheme(): Resources.Theme? {
        // Get theme from helper
        return dropDownHelper.dropDownViewTheme
    }

}