package com.project.ianime.widget.actionbar

import androidx.appcompat.widget.Toolbar
import com.project.ianime.root.BaseFragment

/**
 * Interface to interact with Action Bar
 */
interface ActionBarService {
    /**
     * Sets action bar title
     * @param [title] - text to show on Appbar
     * @param [toolbar] - Appbar reference
     */
    fun setTitle(title: String, toolbar: Toolbar)

    /**
     * Implements navigate back button on Appbar
     * @param [toolbar] - Appbar reference
     * @param [activeFragment] - Current Fragment to show
     */
    fun setNavigateBackAction(toolbar: Toolbar, activeFragment: BaseFragment)
}