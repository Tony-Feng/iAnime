package com.project.ianime.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.ianime.R

class NavigationManagerImpl(
    private val fragmentManager: FragmentManager
) : NavigationManager {

    override fun showFragment(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showFragmentReplaceTop(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun closeTop(): Boolean {
        if (fragmentManager.backStackEntryCount < 1){
            return false
        }
        fragmentManager.popBackStack()
        return true
    }

}