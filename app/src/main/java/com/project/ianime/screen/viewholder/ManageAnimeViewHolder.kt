package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.project.ianime.R
import com.project.ianime.databinding.FragmentManageAnimeBinding
import com.project.ianime.root.FragmentViewHolder

class ManageAnimeViewHolder: FragmentViewHolder() {
    private var _binding: FragmentManageAnimeBinding? = null
    val binding get()= _binding!!
    lateinit var toolbar: Toolbar
    lateinit var addProfileButton: Button
    lateinit var profilePreview: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManageAnimeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar
        addProfileButton = binding.addCoverButton
        profilePreview = binding.profilePreview
        return binding.root
    }

    override fun getContainerViewId(): Int {
        return R.id.container
    }
}