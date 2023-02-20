package com.project.ianime.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.ianime.R

class HomeFragmentViewHolder : FragmentViewHolder() {

    lateinit var toolbar: Toolbar
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addAnimeButton:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        toolbar = rootView.findViewById(R.id.tool_bar)
        bottomNavigationView = rootView.findViewById(R.id.bottom_navigation_view)
        addAnimeButton = rootView.findViewById(R.id.button_add_anime)
        return rootView
    }

    override fun getContainerViewId(): Int {
        return R.id.fragment_container
    }

}