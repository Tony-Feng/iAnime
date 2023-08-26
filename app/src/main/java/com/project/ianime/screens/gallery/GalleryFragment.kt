package com.project.ianime.screens.gallery

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
import com.project.ianime.screens.viewanime.AnimeDetailFragment
import com.project.ianime.service.TestDataRepository
import com.project.ianime.viewmodels.AnimeViewModel
import com.project.ianime.viewmodels.AnimeViewModelFactory
import javax.inject.Inject

/**
 * Gallery screen which shows all the anime in preview mode
 */
class GalleryFragment : BaseFragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    lateinit var animeViewModel: AnimeViewModel

    @Inject
    lateinit var animeViewModelFactory: AnimeViewModelFactory

    private val testDataRepository = TestDataRepository()

    lateinit var animeCardList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val animeApplication = requireActivity().application as AnimeApplication
        animeApplication.applicationComponent.inject(this)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        animeViewModel = ViewModelProvider(this, animeViewModelFactory)[AnimeViewModel::class.java]

        // set up UI elements
        animeCardList = binding.animeList

        // observe the change of the state of the screen to show empty, success and error screen
        animeViewModel.animeUiState.observe(viewLifecycleOwner){ uiState ->
            when (uiState){
                AnimeUiState.Empty -> TODO()
                is AnimeUiState.Error -> TODO()
                AnimeUiState.Success -> loadAnimeGallery()
            }
        }

        // load anime gallery list
//        galleryViewModel.getAnimeGalleryList()

        return binding.root
    }

    /**
     * set recycler view and load gallery items
     */
    private fun loadAnimeGallery(){
        testDataRepository.loadAnimeList()
        // set recycler view
        animeCardList.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val animeCardAdapter = AnimeItemAdapter {
            appNavigation.showFragmentReplaceTop(AnimeDetailFragment.newInstance(it.animeId), baseContainerId)
        }
        animeCardList.adapter = animeCardAdapter
        val animeCardListObserver = Observer<List<AnimeGalleryItem>> { animeItems ->
            animeCardAdapter.submitList(animeItems)
        }

        testDataRepository.testAnimeList.observe(viewLifecycleOwner, animeCardListObserver)

        // observe any changes of data to update recycler view
        animeViewModel.animeGalleryList.observe(viewLifecycleOwner){ galleryList ->
            animeCardAdapter.submitList(galleryList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}