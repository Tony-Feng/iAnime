package com.project.ianime

import android.os.Bundle
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.screen.HomeFragment

class HomeActivity :
    BaseContainerActivity<HomeContainerViewHolder>(HomeContainerViewHolder::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigateToHome()
    }

    private fun navigateToHome(){
        supportFragmentManager.beginTransaction()
            .replace(containerViewHolder.getContainerViewId(), HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}