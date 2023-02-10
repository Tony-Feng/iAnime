package com.project.ianime.navigation

import androidx.fragment.app.Fragment

/**
 *  Is responsible for handling navigation between fragments.
 */

interface NavigationManager {

    /**
     * Adds and shows [fragment] on top
     *
     * @param fragment - fragment to add on top
     */
    fun showFragment(fragment: Fragment)

    /**
     * Replaces top fragment and shows [fragment]
     *
     * @param fragment - fragment to show
     */
    fun showFragmentReplaceTop(fragment: Fragment)

    /**
     * Closes top fragment
     *
     * @return 'true' - if top fragment is closed. 'false' - if no closable fragment exists
     */
    fun closeTop(): Boolean

}