package com.project.ianime.actionbar

import androidx.appcompat.widget.Toolbar

/**
 * Interface to interact with Action Bar
 */
interface ActionBarService {
    /**
     * Sets action bar title
     */
    fun setTitle(title: String, toolbar: Toolbar)

}