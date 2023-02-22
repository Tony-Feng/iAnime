package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.R
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.root.FragmentViewHolder

class GalleryViewHolder: FragmentViewHolder() {
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

    override fun getContainerViewId(): Int {
        return R.id.fragment_container
    }

}