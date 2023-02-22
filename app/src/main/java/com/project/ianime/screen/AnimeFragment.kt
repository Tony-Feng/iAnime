package com.project.ianime.screen

import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.viewholder.AnimeViewHolder

class AnimeFragment: BaseFragment<AnimeViewHolder>(AnimeViewHolder::class.java) {

    companion object{
        fun newInstance() = AnimeFragment()
    }
}