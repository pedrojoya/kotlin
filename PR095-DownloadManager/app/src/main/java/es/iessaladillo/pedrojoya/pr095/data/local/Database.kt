package es.iessaladillo.pedrojoya.pr095.data.local

import es.iessaladillo.pedrojoya.pr095.data.model.Song
import java.util.*

object Database: Repository {

    private val songs: ArrayList<Song> = arrayListOf(
            Song("Morning Mood", "3:43", "Grieg",
                    "https://www.youtube.com/audiolibrary_download?vid=036500ffbf472dcc"),
            Song("Brahms Lullaby", "1:46", "Ron Meixsell",
                    "https://www.youtube.com/audiolibrary_download?vid=9894a50b486c6136"),
            Song("Triangles", "3:05", "Silent Partner",
                    "https://www.youtube.com/audiolibrary_download?vid=8c9219f54213cb4f"),
            Song("From Russia With Love", "2:26", "Huma-Huma",
                    "https://www.youtube.com/audiolibrary_download?vid=4e8d1a0fdb3bbe12"),
            Song("Les Toreadors from Carmen", "2:21", "Bizet",
                    "https://www.youtube.com/audiolibrary_download?vid=fafb35a907cd6e73"),
            Song("Funeral March", "9:25", "Chopin",
                    "https://www.youtube.com/audiolibrary_download?vid=4a7d058f20d31cc4"),
            Song("Dancing on Green Grass", "1:54", "The Green Orbs",
                    "https://www.youtube.com/audiolibrary_download?vid=81cb790358aa232c"),
            Song("Roller Blades", "2:10", "Otis McDonald",
                    "https://www.youtube.com/audiolibrary_download?vid=42b9cb1799a7110f"),
            Song("Aurora Borealis", "1:40", "Bird Creek",
                    "https://www.youtube.com/audiolibrary_download?vid=71e7af02e3fde394"),
            Song("Sour Tennessee Red", "2:11", "John Deley and the 41",
                    "https://www.youtube.com/audiolibrary_download?vid=f24590587cad9a9b"),
            Song("Water Lily", "2:09", "The 126ers",
                    "https://www.youtube.com/audiolibrary_download?vid=5875315a21edd73b"),
            Song("Redhead From Mars", "3:29", "Silent Partner",
                    "https://www.youtube.com/audiolibrary_download?vid=7b17c89cc371a1bc"),
            Song("Destructoid", "1:34", "MK2",
                    "https://www.youtube.com/audiolibrary_download?vid=5ad1f342b4676fc1")
    )


    override fun querySongs(): List<Song> = songs

}
