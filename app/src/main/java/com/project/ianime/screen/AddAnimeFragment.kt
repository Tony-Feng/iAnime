package com.project.ianime.screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.viewholder.AddAnimeFragmentViewHolder
import com.project.ianime.screen.viewmodel.AddAnimeViewModel

class AddAnimeFragment : BaseFragment<AddAnimeFragmentViewHolder>(AddAnimeFragmentViewHolder::class.java) {

    private lateinit var viewModel: AddAnimeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAnimeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = AddAnimeFragment()
    }

}