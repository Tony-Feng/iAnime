package com.project.ianime.navigation

import androidx.fragment.app.Fragment

/**
 *  Is responsible for handling navigation between fragments.
 */

interface NavigationManager {

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
     * Closes top fragment
     *
     * @return 'true' - if top fragment is closed. 'false' - if no closable fragment exists
     */
    fun closeTop(): Boolean

}