package com.test.app.ui.music.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.app.R
import com.test.app.ui.music.model.ArtistViewModel


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ArtistViewHolder>() {

    private var artists = listOf<ArtistViewModel>()
    private var onArtistClicked: OnArtistClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ArtistViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ArtistViewHolder(itemView)
    }

    fun setItems(items: List<ArtistViewModel>) {
        artists = items
        notifyDataSetChanged()
    }

    fun setOnArtistClicked(onArtistClicked: OnArtistClicked) {
        this.onArtistClicked = onArtistClicked
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artistViewModel = artists[position]
        holder.bindView(artistViewModel)
        holder.itemView.setOnClickListener { onArtistClicked?.onClicked(artistViewModel) }
    }

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistPicture = itemView.findViewById<ImageView>(R.id.artist_picture)
        private val artistName = itemView.findViewById<TextView>(R.id.artist_name)

        fun bindView(artistViewModel: ArtistViewModel) {
            artistName.text = artistViewModel.name
            artistViewModel.picture?.let {
                Glide.with(artistPicture)
                    .load(it)
                    .apply(RequestOptions.circleCropTransform())
                    .into(artistPicture)
            }
        }
    }

    interface OnArtistClicked {
        fun onClicked(artistViewModel: ArtistViewModel)
    }
}