package com.project.ianime

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.screen.GalleryFragment
import com.project.ianime.screen.UserFragment

class HomeActivity :
    BaseContainerActivity<HomeContainerViewHolder>(HomeContainerViewHolder::class.java) {

    private lateinit var bottomNavigationView: BottomNavigationView
    private val galleryFragment = GalleryFragment()
    private val userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Activate Setting Menu
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolBar)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        navigationManager.showFragmentReplaceTop(galleryFragment)

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
    }

}