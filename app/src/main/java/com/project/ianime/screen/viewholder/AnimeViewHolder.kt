package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.project.ianime.databinding.FragmentAnimeBinding
import com.project.ianime.root.FragmentViewHolder

class AnimeViewHolder: FragmentViewHolder() {
    private var _binding: FragmentAnimeBinding? = null
    val binding get()= _binding!!
    lateinit var toolbar: Toolbar
    lateinit var animeProfile: ImageView
    lateinit var animeName: TextView
    lateinit var animeType: TextView
    lateinit var animeCountry: TextView
    lateinit var animePublishedYear: TextView
    lateinit var animeStatus: TextView
    lateinit var animeIntro: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar
        animeProfile = binding.animeProfile
        animeName = binding.animeName
        animeType = binding.animeType
        animeCountry = binding.animeCountry
        animePublishedYear = binding.animeYear
        animeStatus = binding.animeStatus
        animeIntro = binding.animeIntro
        return binding.root
    }
}