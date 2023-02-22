package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.ianime.databinding.FragmentAnimeBinding
import com.project.ianime.root.FragmentViewHolder

class AnimeViewHolder: FragmentViewHolder() {
    private var _binding: FragmentAnimeBinding? = null
    val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun getContainerViewId(): Int {
        TODO("Not yet implemented")
    }
}