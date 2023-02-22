package com.project.ianime.screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.project.ianime.R
import com.project.ianime.screen.viewmodel.AddAnimeViewModel

class AddAnimeFragment : ManageAnimeFragment() {

    private lateinit var viewModel: AddAnimeViewModel
    override fun updateActionBar() {
        actionBarService.setTitle(getString(R.string.add_anime_title), viewHolder.toolbar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAnimeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = AddAnimeFragment()
    }

}