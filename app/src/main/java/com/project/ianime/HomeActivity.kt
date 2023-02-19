package com.project.ianime

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.screen.AddAnimeFragment
import com.project.ianime.screen.GalleryFragment
import com.project.ianime.screen.UserFragment

class HomeActivity :
    BaseContainerActivity<HomeContainerViewHolder>(HomeContainerViewHolder::class.java) {

    private lateinit var bottomNavigationView: BottomNavigationView
    private val galleryFragment = GalleryFragment.newInstance()
    private val userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolBar = findViewById<Toolbar>(viewHolder.getActionBarViewId())
        setSupportActionBar(toolBar)

        bottomNavigationView = findViewById(viewHolder.getBottomBarViewId())
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    navigationManager.showFragmentReplaceTop(galleryFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.user_screen -> {
                    navigationManager.showFragmentReplaceTop(userFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        val addAnimeButton = findViewById<FloatingActionButton>(R.id.button_add_anime)
        addAnimeButton.setOnClickListener{
            navigateToAddScreen()
        }

        navigationManager.showFragmentReplaceTop(galleryFragment)
    }

    private fun navigateToAddScreen(){
        navigationManager.showFragmentReplaceTop(AddAnimeFragment.newInstance())
    }

}