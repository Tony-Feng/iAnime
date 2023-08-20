package com.project.ianime.screens.gallery

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.di.AnimeApplication
import com.project.ianime.root.BaseFragment
import com.project.ianime.screens.gallery.adapter.AnimeItemAdapter
import com.project.ianime.screens.stateholder.AnimeUiState
import com.project.ianime.screens.viewanime.AnimeFragment
import com.project.ianime.service.TestDataRepository
import com.project.ianime.viewmodels.GalleryViewModel
import com.project.ianime.viewmodels.GalleryViewModelFactory
import javax.inject.Inject

// TODO: Consider separate filter and recyclerview layouts
class GalleryFragment : BaseFragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    lateinit var galleryViewModel: GalleryViewModel

    @Inject
    lateinit var galleryViewModelFactory: GalleryViewModelFactory

    private val testDataRepository = TestDataRepository()

    lateinit var animeCardList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val app = requireActivity().application as AnimeApplication
        app.applicationComponent.inject(this)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        galleryViewModel = ViewModelProvider(this, galleryViewModelFactory)[GalleryViewModel::class.java]

        // set up UI elements
        animeCardList = binding.animeList

        // observe the change of the state of the screen to show empty, success and error screen
        galleryViewModel.animeUiState.observe(viewLifecycleOwner){ uiState ->
            when (uiState){
                AnimeUiState.Empty -> TODO()
                is AnimeUiState.Error -> TODO()
                AnimeUiState.Success -> {
                    loadAnimeGallery()
                }
            }
        }

        // load anime gallery list
        galleryViewModel.getAnimeGalleryList()

        return binding.root
    }

    private fun loadAnimeGallery(){
        testDataRepository.loadAnimeList()
        animeCardList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val animeCardAdapter = AnimeItemAdapter {
            appNavigation.showFragmentReplaceTop(AnimeFragment.newInstance(), baseContainerId)
        }
        animeCardList.adapter = animeCardAdapter
        val animeCardListObserver = Observer<List<AnimeGalleryItem>> { animeItems ->
            animeCardAdapter.submitList(animeItems)
        }

        testDataRepository.testAnimeList.observe(viewLifecycleOwner, animeCardListObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}