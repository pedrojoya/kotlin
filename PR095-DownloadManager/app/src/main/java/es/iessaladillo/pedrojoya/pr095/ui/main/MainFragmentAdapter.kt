package es.iessaladillo.pedrojoya.pr095.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import es.iessaladillo.pedrojoya.pr095.R
import es.iessaladillo.pedrojoya.pr095.data.model.Song
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter(context: Context, private val songs: List<Song>, private val listView: ListView) : ArrayAdapter<Song>(context, R.layout.fragment_main_item, songs) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView?: LayoutInflater.from(context).inflate(R.layout.fragment_main_item, parent, false)
        val holder = itemView.tag as? ViewHolder ?: onCreateViewHolder(itemView)
        itemView.tag = holder
        onBindViewHolder(holder, position)
        return itemView
    }

    private fun onCreateViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    private fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(songs[position], position)
    }

    private inner class ViewHolder(override val containerView: View?): LayoutContainer {

        fun bind(song: Song, position: Int) {
            lblName.text = song.name
            lblDuration.text = song.duration
            lblAuthor.text = song.author
            val songFile = song.publicFile
            if (songFile != null) {
                imgPlaying.setImageResource(if (listView.isItemChecked(
                                position))
                    R.drawable.ic_equalizer_black_24dp
                else
                    R.drawable
                            .ic_play_circle_outline_black_24dp)

            } else {
                imgPlaying.setImageResource(R.drawable.ic_file_download_black_24dp)
            }
        }

    }
}
