package com.project.ianime.screens.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.project.ianime.HomeActivity
import com.project.ianime.api.error.ErrorType
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.di.AnimeApplication
import com.project.ianime.root.BaseFragment
import com.project.ianime.screens.gallery.adapter.AnimeItemAdapter
import com.project.ianime.screens.stateholder.AnimeUiState
import com.project.ianime.screens.viewanime.AnimeDetailFragment
import com.project.ianime.viewmodels.AnimeViewModel

/**
 * Gallery screen which shows all the anime in preview mode
 */
class GalleryFragment : BaseFragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var animeViewModel: AnimeViewModel

    lateinit var animeCardList: RecyclerView
    lateinit var refreshAction: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        animeViewModel = ViewModelProvider(requireActivity(), (activity as HomeActivity).animeViewModelFactory)[AnimeViewModel::class.java]

        // set up UI elements
        animeCardList = binding.animeListContainer
        refreshAction = binding.swipeContainer

        // observe the change of the state of the screen to show empty, success and error screen
        animeViewModel.animeUiState.observe(viewLifecycleOwner){ uiState ->
            when (uiState){
                AnimeUiState.Empty -> showEmptyScreen()
                is AnimeUiState.Error -> showErrorScreen(uiState.errorType)
                AnimeUiState.Success -> showAnimeGallery()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set up refresh listener
        refreshAction.setOnRefreshListener {
            animeViewModel.getAnimeList(true)
        }
    }

    /**
     * set recycler view and load gallery items
     */
    private fun showAnimeGallery(){
        // update UI state
        refreshAction.isRefreshing = false
        binding.errorContainer.root.visibility = View.GONE
        binding.emptyContainer.root.visibility = View.GONE
        animeCardList.visibility = View.VISIBLE

        // set recycler view
        animeCardList.layoutManager = GridLayoutManager(requireContext(), 2)
        val animeCardAdapter = AnimeItemAdapter {
            // navigate to anime detail screen
            val bundle = Bundle().apply {
                putString(ANIME_ID_PARAM, it.animeId)
            }
            val animeDetailFragment = AnimeDetailFragment()
            animeDetailFragment.arguments = bundle
            appNavigation.showFragmentReplaceTop(animeDetailFragment, baseContainerId)
        }
        animeCardList.adapter = animeCardAdapter

        // observe any changes of data to update recycler view
        animeViewModel.animeList.observe(viewLifecycleOwner){ animeList ->
            animeCardAdapter.submitList(animeList)
        }
    }

    private fun showErrorScreen(errorType: ErrorType){
        // update UI state
        val errorContainer = binding.errorContainer
        refreshAction.isRefreshing = false
        animeCardList.visibility = View.GONE
        binding.emptyContainer.root.visibility = View.GONE
        errorContainer.root.visibility = View.VISIBLE
        errorContainer.errorTitle.text = getString(errorType.label)
        errorContainer.errorMessage.text = getString(errorType.message)
    }

    private fun showEmptyScreen(){
        // update UI state
        val emptyContainer = binding.emptyContainer
        refreshAction.isRefreshing = false
        animeCardList.visibility = View.GONE
        binding.errorContainer.root.visibility = View.GONE
        emptyContainer.root.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ANIME_ID_PARAM = "anime_id"
    }
}