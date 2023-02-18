package com.project.ianime.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.project.ianime.adapter.AnimeItemAdapter
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.model.AnimeItemModel
import com.project.ianime.service.TestDataRepository

// TODO: Consider separate filter and recyclerview layouts
class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val binding get()= _binding!!
    private val testDataRepository =  TestDataRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        testDataRepository.loadAnimeList()

        val animeCardList = binding.animeList
        animeCardList.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val animeCardAdapter = AnimeItemAdapter {
            // TODO: Navigate to anime detail screen
        }

        animeCardList.adapter = animeCardAdapter
        val animeCardListObserver = Observer<List<AnimeItemModel>>{ animeItems ->
            animeCardAdapter.submitList(animeItems)
        }

        testDataRepository.testAnimeList.observe(viewLifecycleOwner, animeCardListObserver)

        return binding.root
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