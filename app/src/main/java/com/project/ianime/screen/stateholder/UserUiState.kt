package com.project.ianime.screen.stateholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.ianime.databinding.FragmentUserBinding
import com.project.ianime.root.FragmentUiState

class UserUiState: FragmentUiState() {
    private var _binding: FragmentUserBinding? = null
    val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
    }

}