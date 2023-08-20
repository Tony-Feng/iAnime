package com.project.ianime.screens.viewanime

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.project.ianime.R
import com.project.ianime.databinding.FragmentAnimeBinding
import com.project.ianime.root.BaseFragment
import com.project.ianime.screens.manageanime.EditAnimeFragment
import com.project.ianime.utils.Constants
import com.project.ianime.utils.updateLanguageSetting

class AnimeFragment : BaseFragment() {
    private var _binding: FragmentAnimeBinding? = null
    val binding get() = _binding!!
    lateinit var toolbar: Toolbar
    lateinit var animeProfile: ImageView
    lateinit var animeName: TextView
    lateinit var animeType: TextView
    lateinit var animeCountry: TextView
    lateinit var animePublishedYear: TextView
    lateinit var animeStatus: TextView
    lateinit var animeIntro: TextView

    override fun updateActionBar(): Boolean {
        //TODO: Update to each anime name
        actionBarService.setTitle(getString(R.string.app_name), toolbar)
        actionBarService.setNavigateBackAction(toolbar, this)
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar
        animeProfile = binding.animeProfile
        animeName = binding.animeName
        animeType = binding.animeType
        animeCountry = binding.animeCountry
        animePublishedYear = binding.animeYear
        animeStatus = binding.animeStatus
        animeIntro = binding.animeIntro
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Test using mocked data
        getAnimeDetails()
    }

    //TODO: Update using Get API
    private fun getAnimeDetails() {
        animeProfile.setImageResource(R.drawable.ic_gallery)
        animeName.text = "Throne of Seal"
        animeCountry.text = getString(R.string.anime_country_title, "China")
        animeType.text = getString(R.string.anime_type_title, "God")
        animePublishedYear.text = getString(R.string.anime_year_title, "2021")
        animeStatus.text = getString(R.string.anime_status_title, "In Progress")
        animeIntro.text = "A boy on his own way to fight"

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit_anime -> {
                appNavigation.showFragmentOverTop(
                    EditAnimeFragment.newInstance(),
                    baseContainerId
                )
                true
            }
            R.id.setting_chinese -> {
                updateLanguageSetting(requireContext(), Constants.LANG_ZH)
                true
            }
            R.id.setting_english -> {
                updateLanguageSetting(requireContext(), Constants.LANG_EN)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AnimeFragment()
    }
}