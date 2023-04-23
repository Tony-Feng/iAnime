package com.project.ianime.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.adapter.AnimeItemAdapter
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.viewmodels.AnimeItemUiState
import com.project.ianime.root.BaseFragment
import com.project.ianime.viewmodels.GalleryViewModel
import com.project.ianime.service.TestDataRepository

// TODO: Consider separate filter and recyclerview layouts
class GalleryFragment : BaseFragment(){

    private lateinit var viewModel: GalleryViewModel
    private val testDataRepository =  TestDataRepository()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get()= _binding!!
    lateinit var animeCardList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        animeCardList = binding.animeList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testDataRepository.loadAnimeList()
        val animeCardList = animeCardList
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = GalleryFragment()
    }

}