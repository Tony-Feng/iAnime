package com.project.ianime.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.R
import com.project.ianime.screen.AddAnimeFragment
import com.project.ianime.screen.GalleryFragment
import com.project.ianime.screen.UserFragment


class HomeFragment: BaseFragment<HomeFragmentViewHolder>(HomeFragmentViewHolder::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationManager.showFragmentReplaceTop(GalleryFragment.newInstance())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(viewHolder.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        viewHolder.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    navigationManager.showFragmentReplaceTop(GalleryFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.user_screen -> {
                    navigationManager.showFragmentReplaceTop(UserFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        viewHolder.addAnimeButton.setOnClickListener{
            navigateToAddScreen()
        }

    }

    private fun navigateToAddScreen(){
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, AddAnimeFragment.newInstance()).addToBackStack(null).commit()
    }


    companion object {
        fun newInstance() = HomeFragment()
    }

}