package com.project.ianime.screens.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.databinding.FragmentGalleryBinding
import com.project.ianime.root.BaseFragment
import com.project.ianime.screens.gallery.adapter.AnimeItemAdapter
import com.project.ianime.screens.viewanime.AnimeFragment
import com.project.ianime.service.TestDataRepository

// TODO: Consider separate filter and recyclerview layouts
class GalleryFragment : BaseFragment() {

    private val testDataRepository = TestDataRepository()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
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

    companion object {
        fun newInstance() = GalleryFragment()
    }

}