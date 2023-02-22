package com.project.ianime.actionbar

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ActionBarServiceImpl(private val activity: AppCompatActivity): ActionBarService {

    override fun setTitle(title: String, toolbar: Toolbar) {
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar ?: return
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.title = title
    }
}