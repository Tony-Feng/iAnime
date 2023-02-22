package com.project.ianime.screen.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.ianime.R
import com.project.ianime.databinding.FragmentUserBinding
import com.project.ianime.root.FragmentViewHolder

class UserFragmentViewHolder: FragmentViewHolder() {
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

    override fun getContainerViewId(): Int {
        return R.id.fragment_container
    }

}