package com.project.ianime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ianime.R
import com.project.ianime.viewmodels.AnimeItemUiState

class AnimeItemAdapter(
    private val clickHandler: (AnimeItemUiState) -> Unit
):ListAdapter<AnimeItemUiState, AnimeItemViewHolder>(DIFF_CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        // Set click item event
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }

    companion object{
        val DIFF_CONFIG = object: DiffUtil.ItemCallback<AnimeItemUiState>() {
            override fun areItemsTheSame(
                oldItem: AnimeItemUiState,
                newItem: AnimeItemUiState
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: AnimeItemUiState,
                newItem: AnimeItemUiState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}