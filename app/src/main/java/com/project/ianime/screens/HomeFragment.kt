package com.project.ianime.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.ianime.R
import com.project.ianime.databinding.FragmentHomeBinding
import com.project.ianime.root.BaseFragment

class HomeFragment : BaseFragment() {
    lateinit var toolbar: Toolbar
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addAnimeButton: FloatingActionButton
    private var _binding: FragmentHomeBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar
        bottomNavigationView = binding.bottomNavigationView
        addAnimeButton = binding.buttonAddAnime
        return binding.root
    }

    override fun updateActionBar(): Boolean {
        actionBarService.setTitle(getString(R.string.app_name), toolbar)
        return true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Launch Main Screen
        navigateToMainScreen()

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    appNavigation.showFragmentOverTop(
                        GalleryFragment.newInstance(),
                        fragmentContainerId
                    )
                    return@setOnItemSelectedListener true
                }
                R.id.user_screen -> {
                    appNavigation.showFragmentOverTop(
                        UserFragment.newInstance(),
                        fragmentContainerId
                    )
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        addAnimeButton.setOnClickListener {
            navigateToAddScreen()
        }
    }
    private fun navigateToMainScreen(){
        appNavigation.showFragmentReplaceTop(GalleryFragment.newInstance(), fragmentContainerId)
    }

    private fun navigateToAddScreen() {
        appNavigation.showFragmentReplaceTop(AddAnimeFragment.newInstance(), baseContainerId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}