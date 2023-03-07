package com.project.ianime.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.ianime.databinding.FragmentHomeBinding
import com.project.ianime.root.FragmentViewHolder

class HomeFragmentViewHolder : FragmentViewHolder() {

    lateinit var toolbar: Toolbar
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addAnimeButton:FloatingActionButton
    private var _binding: FragmentHomeBinding? = null
    val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar
        bottomNavigationView = binding.bottomNavigationView
        addAnimeButton = binding.buttonAddAnime
        return binding.root
    }

}