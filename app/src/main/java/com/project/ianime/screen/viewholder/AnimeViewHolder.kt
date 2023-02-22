package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.project.ianime.databinding.FragmentAnimeBinding
import com.project.ianime.root.FragmentViewHolder

class AnimeViewHolder: FragmentViewHolder() {
    private var _binding: FragmentAnimeBinding? = null
    val binding get()= _binding!!
    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar
        return binding.root
    }

    override fun getContainerViewId(): Int {
        TODO("Not yet implemented")
    }
}