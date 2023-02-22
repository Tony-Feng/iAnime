package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.ianime.R
import com.project.ianime.databinding.FragmentAddAnimeBinding
import com.project.ianime.root.FragmentViewHolder

class AddAnimeFragmentViewHolder: FragmentViewHolder() {
    private var _binding: FragmentAddAnimeBinding? = null
    val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getContainerViewId(): Int {
        return R.id.container
    }
}