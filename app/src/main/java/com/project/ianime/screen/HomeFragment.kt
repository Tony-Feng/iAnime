package com.project.ianime.screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.R
import com.project.ianime.root.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentViewHolder>(HomeFragmentViewHolder::class.java) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Launch Main Screen
        navigateToMainScreen()

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(viewHolder.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        viewHolder.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    navigationManager.showFragmentOverTop(
                        GalleryFragment.newInstance(),
                        viewHolder.getContainerViewId()
                    )
                    return@setOnItemSelectedListener true
                }
                R.id.user_screen -> {
                    navigationManager.showFragmentOverTop(
                        UserFragment.newInstance(),
                        viewHolder.getContainerViewId()
                    )
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        viewHolder.addAnimeButton.setOnClickListener {
            navigateToAddScreen()
        }
    }
    private fun navigateToMainScreen(){
        navigationManager.showFragmentOverTop(GalleryFragment.newInstance(), viewHolder.getContainerViewId())
    }

    private fun navigateToAddScreen() {
        navigationManager.showFragmentReplaceTop(AddAnimeFragment.newInstance(), baseContainerId)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}