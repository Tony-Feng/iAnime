package com.project.ianime.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.project.ianime.adapter.AnimeItemAdapter
import com.project.ianime.model.AnimeItemUiState
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.stateholder.GalleryUiState
import com.project.ianime.screen.viewmodel.GalleryViewModel
import com.project.ianime.service.TestDataRepository

// TODO: Consider separate filter and recyclerview layouts
class GalleryFragment : BaseFragment<GalleryUiState>(GalleryUiState::class.java) {

    private lateinit var viewModel: GalleryViewModel
    private val testDataRepository =  TestDataRepository()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testDataRepository.loadAnimeList()
        val animeCardList = uiState.animeCardList
        animeCardList.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val animeCardAdapter = AnimeItemAdapter {
            appNavigation.showFragmentReplaceTop(AnimeFragment.newInstance(), baseContainerId)
        }
        animeCardList.adapter = animeCardAdapter
        val animeCardListObserver = Observer<List<AnimeItemUiState>>{ animeItems ->
            animeCardAdapter.submitList(animeItems)
        }

        testDataRepository.testAnimeList.observe(viewLifecycleOwner, animeCardListObserver)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = GalleryFragment()
    }

}