package es.iessaladillo.pedrojoya.pr095.ui.main

import android.content.Context
import android.view.View
import android.widget.ListView
import es.iessaladillo.pedrojoya.pr095.R
import es.iessaladillo.pedrojoya.pr095.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr095.data.model.Song
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter(context: Context, data: List<Song>, private val listView: ListView) :
        AdapterViewBaseAdapter<Song, MainFragmentAdapter.ViewHolder>(data, R.layout.fragment_main_item) {

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    inner class ViewHolder(override val containerView: View?): LayoutContainer {

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
