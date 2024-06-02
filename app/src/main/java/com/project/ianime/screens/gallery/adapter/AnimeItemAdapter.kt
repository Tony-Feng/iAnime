package com.project.ianime.screens.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ianime.R
import com.project.ianime.api.model.AnimeApiModel

class AnimeItemAdapter(
    private val clickHandler: (String) -> Unit
) : ListAdapter<AnimeApiModel, AnimeItemViewHolder>(DIFF_CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimeItemViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
        // Set click item event
        holder.itemView.setOnClickListener {
            clickHandler(anime.animeId)
        }
    }

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<AnimeApiModel>() {
            override fun areItemsTheSame(
                oldItem: AnimeApiModel,
                newItem: AnimeApiModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: AnimeApiModel,
                newItem: AnimeApiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}