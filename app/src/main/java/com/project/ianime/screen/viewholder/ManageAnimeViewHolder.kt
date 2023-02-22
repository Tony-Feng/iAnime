package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.ianime.R
import com.project.ianime.databinding.FragmentManageAnimeBinding
import com.project.ianime.root.FragmentViewHolder

class ManageAnimeViewHolder: FragmentViewHolder() {
    private var _binding: FragmentManageAnimeBinding? = null
    val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManageAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getContainerViewId(): Int {
        return R.id.container
    }
}