package com.project.ianime.screen

import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.R
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.viewholder.AnimeViewHolder

class AnimeFragment: BaseFragment<AnimeViewHolder>(AnimeViewHolder::class.java) {

    companion object{
        fun newInstance() = AnimeFragment()
    }

    override fun updateActionBar() {
        //TODO: Update to each anime name
        actionBarService.setTitle(getString(R.string.app_name), viewHolder.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}