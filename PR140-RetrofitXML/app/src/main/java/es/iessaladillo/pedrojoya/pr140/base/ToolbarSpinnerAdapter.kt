package es.iessaladillo.pedrojoya.pr140.base

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import androidx.appcompat.widget.ThemedSpinnerAdapter

// Adapter for toolbar spinner. Must implement ThemedSpinnerAdapter and
// receive toolbar context.
class ToolbarSpinnerAdapter<T>(context: Context, private val data: List<T>) :
        ArrayAdapter<T>(context, android.R.layout.simple_spinner_dropdown_item, data),
        ThemedSpinnerAdapter {

    // Helper in order to be drawn with the right theme
    private val helper: ThemedSpinnerAdapter.Helper = ThemedSpinnerAdapter.Helper(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: helper.dropDownViewInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        return (itemView ?: helper.dropDownViewInflater.inflate(android.R.layout
                .simple_spinner_dropdown_item, parent, false)).apply {
            // android.R.layout.simple_spinner_dropdown_item layout has only a CheckedTextView.
            (this as CheckedTextView).text = data[position].toString()
        }
    }

    override fun setDropDownViewTheme(theme: Resources.Theme?) {
        // Set helper's theme
        helper.dropDownViewTheme = theme
    }

    override fun getDropDownViewTheme(): Resources.Theme? {
        // Get helper's theme
        return helper.dropDownViewTheme
    }

}
