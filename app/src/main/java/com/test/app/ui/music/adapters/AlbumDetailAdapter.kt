package com.test.app.ui.music.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.app.R
import com.test.app.ui.music.model.AlbumViewModel
import com.test.app.ui.music.model.SongViewModel

class AlbumDetailAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HEADER_TYPE = 0
        const val TRACK_TYPE = 1
    }

    private var tracks = listOf<SongViewModel>()
    private var album: AlbumViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return when (position) {
            HEADER_TYPE -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.item_album_detail_header, parent, false)
                AlbumHeaderViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.item_track, parent, false)
                TrackItemViewHolder(itemView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> HEADER_TYPE
            else -> TRACK_TYPE
        }
    }

    fun setAlbum(album: AlbumViewModel) {
        this.album = album
    }

    fun setItems(items: List<SongViewModel>) {
        tracks = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tracks.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            holder is AlbumHeaderViewHolder -> holder.bindView(album)
            holder is TrackItemViewHolder && position > 0 -> holder.bindView(
                tracks[position - 1],
                position
            )
        }
    }

    class TrackItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackName = itemView.findViewById<TextView>(R.id.track_name)
        private val artistNameView = itemView.findViewById<TextView>(R.id.artist_name)
        private val trackNumber = itemView.findViewById<TextView>(R.id.number)

        fun bindView(artistViewModel: SongViewModel, number: Int) {
            artistNameView.text = artistViewModel.artistViewModel.name
            trackName.text = artistViewModel.songName
            trackNumber.text = number.toString()
        }
    }

    class AlbumHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumCover = itemView.findViewById<ImageView>(R.id.album_cover)
        private val albumName = itemView.findViewById<TextView>(R.id.album_name)
        private val artistNameView = itemView.findViewById<TextView>(R.id.artist_name)

        fun bindView(album: AlbumViewModel?) {
            album?.let {
                artistNameView.text = it.artist
                albumName.text = it.title
                artistNameView.text = it.artist
                Glide.with(albumCover)
                    .load(it.cover)
                    .into(albumCover)
            }
        }
    }


}