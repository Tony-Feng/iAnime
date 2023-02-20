package com.project.ianime.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.ianime.root.FragmentViewHolder
import com.project.ianime.root.ViewHolder

//TODO 2023-02-18: Optimize Navigation method (add)
class NavigationManagerImpl(
    private val fragmentManager: FragmentManager,
    private val viewContainer: FragmentViewHolder
) : NavigationManager {

    override fun showFragmentPermanent(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .add(viewContainer.getContainerViewId(), fragment)
            .commit()
    }

    override fun showFragmentReplaceTop(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .replace(viewContainer.getContainerViewId(), fragment)
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