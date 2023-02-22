package com.project.ianime.screen

import com.project.ianime.R
class EditAnimeFragment: ManageAnimeFragment() {
    override fun updateActionBar() {
        actionBarService.setTitle(getString(R.string.edit_anime_title), viewHolder.toolbar)
    }
}