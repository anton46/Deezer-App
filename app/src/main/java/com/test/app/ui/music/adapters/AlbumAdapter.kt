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

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumItemViewHolder>() {
    private var albums = listOf<AlbumViewModel>()
    private var artistName: String = ""
    private var onAlbumClicked: OnAlbumClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AlbumItemViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return AlbumItemViewHolder(itemView)
    }

    fun setItems(items: List<AlbumViewModel>) {
        albums = items
        notifyDataSetChanged()
    }

    fun setArtistName(artistName: String) {
        this.artistName = artistName
    }

    fun setOnAlbumClickListener(onAlbumClicked: OnAlbumClicked) {
        this.onAlbumClicked = onAlbumClicked
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        val album = albums[position]
        holder.bindView(album, artistName)
        holder.itemView.setOnClickListener { onAlbumClicked?.onClicked(album.copy(artist = artistName)) }
    }

    class AlbumItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumCover = itemView.findViewById<ImageView>(R.id.album_cover)
        private val albumName = itemView.findViewById<TextView>(R.id.album_name)
        private val artistNameView = itemView.findViewById<TextView>(R.id.artist_name)

        fun bindView(artistViewModel: AlbumViewModel, artistName: String) {
            albumName.text = artistViewModel.title
            artistNameView.text = artistName
            Glide.with(albumCover)
                .load(artistViewModel.cover)
                .into(albumCover)
        }
    }

    interface OnAlbumClicked {
        fun onClicked(album: AlbumViewModel)
    }
}