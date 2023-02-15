package com.project.ianime.screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.project.ianime.R
import com.project.ianime.adapter.AnimeItemAdapter
import com.project.ianime.databinding.FragmentGalleryBinding

// TODO: Consider separate filter and recyclerview layouts
// TODO: Consider building BaseFragment
class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val animeCardList = binding.animeList
        animeCardList.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, true)
        val animeCardAdapter = AnimeItemAdapter {
            // TODO: Navigate to anime detail screen
        }

        animeCardList.adapter = animeCardAdapter

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