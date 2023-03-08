package com.project.ianime.screen.stateholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.root.FragmentUiState

class GalleryUiState: FragmentUiState() {
    private var _binding: FragmentGalleryBinding? = null
    val binding get()= _binding!!
    lateinit var animeCardList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        animeCardList = binding.animeList

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
    }

}