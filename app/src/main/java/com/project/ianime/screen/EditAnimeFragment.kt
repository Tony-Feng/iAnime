package com.project.ianime.screen

import com.project.ianime.R
class EditAnimeFragment: ManageAnimeFragment() {
    override fun updateActionBar(): Boolean {
        actionBarService.setTitle(getString(R.string.edit_anime_title), viewHolder.toolbar)
        actionBarService.setNavigateBackAction(viewHolder.toolbar, this)
        return true
    }

    override fun saveAnime() {
        TODO("Not yet implemented")
    }

    companion object{
        fun newInstance() = EditAnimeFragment()
    }
}