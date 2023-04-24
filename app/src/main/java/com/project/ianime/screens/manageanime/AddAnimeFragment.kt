package com.project.ianime.screens.manageanime

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.project.ianime.R

class AddAnimeFragment : ManageAnimeFragment() {

    private lateinit var viewModel: AddAnimeViewModel

    override fun updateActionBar(): Boolean {
        actionBarService.setTitle(getString(R.string.add_anime_title), toolbar)
        actionBarService.setNavigateBackAction(toolbar, this)
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAnimeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun saveAnime() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = AddAnimeFragment()
    }

}