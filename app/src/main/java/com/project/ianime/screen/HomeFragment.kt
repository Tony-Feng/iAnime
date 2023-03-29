package com.project.ianime.screen

import android.os.Bundle
import android.view.View
import com.project.ianime.R
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.stateholder.HomeFragmentUiState

class HomeFragment : BaseFragment<HomeFragmentUiState>(HomeFragmentUiState::class.java) {
    override fun updateActionBar(): Boolean {
        actionBarService.setTitle(getString(R.string.app_name), uiState.toolbar)
        return true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Launch Main Screen
        navigateToMainScreen()

        uiState.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    appNavigation.showFragmentOverTop(
                        GalleryFragment.newInstance(),
                        uiState.getContainerViewId()
                    )
                    return@setOnItemSelectedListener true
                }
                R.id.user_screen -> {
                    appNavigation.showFragmentOverTop(
                        UserFragment.newInstance(),
                        uiState.getContainerViewId()
                    )
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        uiState.addAnimeButton.setOnClickListener {
            navigateToAddScreen()
        }
    }
    private fun navigateToMainScreen(){
        appNavigation.showFragmentReplaceTop(GalleryFragment.newInstance(), uiState.getContainerViewId())
    }

    private fun navigateToAddScreen() {
        appNavigation.showFragmentReplaceTop(AddAnimeFragment.newInstance(), baseContainerId)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}