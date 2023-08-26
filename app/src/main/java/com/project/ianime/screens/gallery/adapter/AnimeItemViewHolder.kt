package com.project.ianime.screens.gallery.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.R
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.utils.image.ImageUtils

class AnimeItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val animeImage: ImageView = view.findViewById(R.id.item_image)
    private val animeRate: TextView = view.findViewById(R.id.rate)
    private val animeTitle: TextView = view.findViewById(R.id.item_title)
    private val animeDescription: TextView = view.findViewById(R.id.item_description)
    private val imageUtils: ImageUtils by lazy {
        ImageUtils()
    }

    fun bind(model: AnimeGalleryItem) {
        model.imageUrl?.let {
            imageUtils.loadImageFromDisk(it, animeImage)
        }
        animeImage.setImageResource(R.drawable.ic_anime_placeholder)
        animeRate.text = model.animeRate.toString()
        animeTitle.text = model.animeName
        animeDescription.text = model.animeSynopsis
    }
}