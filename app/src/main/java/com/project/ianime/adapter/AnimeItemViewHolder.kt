package com.project.ianime.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.R
import com.project.ianime.model.AnimeItemModel

class AnimeItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val animeImage: ImageView = view.findViewById(R.id.item_image)
    private val animeTitle: TextView = view.findViewById(R.id.item_title)
    private val animeDescription: TextView = view.findViewById(R.id.item_description)

    fun bind(model: AnimeItemModel){
        //TODO 2022-02-11: Load image icon to item
        animeImage.setImageResource(R.drawable.ic_gallery)
        animeTitle.text = model.animeName
        animeDescription.text = model.animeDescription
    }
}