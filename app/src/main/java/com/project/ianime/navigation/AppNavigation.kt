package com.project.ianime.navigation

import androidx.fragment.app.Fragment

/**
 *  Is responsible for handling navigation between fragments.
 */

interface AppNavigation {

    /**
     * Replaces the active fragment and shows [fragment] on top, previous fragment cannot show with navigate back
     *
     * @param fragment - fragment to add on top
     * @param containerId - container id
     */
    fun showFragmentReplaceTop(fragment: Fragment, containerId: Int)

    /**
     * Replaces top active fragment and adds [fragment] on Top, previous fragment can show with navigate back
     *
     * @param fragment - fragment to show
     * @param containerId - container id
     */
    fun showFragmentOverTop(fragment: Fragment, containerId: Int)

    /**
     * Closes top fragment and navigates back to previous fragment while checking if fragment satisfies navigate back condition
     *
     * @return 'true' - if top fragment is closed. 'false' - if no closable fragment exists
     */
    fun navigateBack(): Boolean

    /**
     * Closes top fragment and navigates back to previous fragment
     *
     * @return 'true' - if top fragment is closed. 'false' - if no closable fragment exists
     */
    fun closeTopFragment(): Boolean

}