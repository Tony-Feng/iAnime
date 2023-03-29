package com.project.ianime.screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.project.ianime.HomeActivity.Companion.SETTING_CHINESE
import com.project.ianime.HomeActivity.Companion.SETTING_ENGLISH
import com.project.ianime.R
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.stateholder.AnimeUiState
import com.project.ianime.utils.updateLanguageSetting

class AnimeFragment : BaseFragment<AnimeUiState>(AnimeUiState::class.java) {
    override fun updateActionBar(): Boolean {
        //TODO: Update to each anime name
        actionBarService.setTitle(getString(R.string.app_name), uiState.toolbar)
        actionBarService.setNavigateBackAction(uiState.toolbar, this)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Test using mocked data
        getAnimeDetails()
    }
    //TODO: Update using Get API
    private fun getAnimeDetails(){
        uiState.animeProfile.setImageResource(R.drawable.ic_gallery)
        uiState.animeName.text = "Throne of Seal"
        uiState.animeCountry.text = getString(R.string.anime_country_title, "China")
        uiState.animeType.text = getString(R.string.anime_type_title, "God")
        uiState.animePublishedYear.text = getString(R.string.anime_year_title, "2021")
        uiState.animeStatus.text = getString(R.string.anime_status_title, "In Progress")
        uiState.animeIntro.text = "A boy on his own way to fight"

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
            R.id.setting_chinese_language -> {
                updateLanguageSetting(requireContext(), SETTING_CHINESE)
                true
            }
            R.id.setting_english_language -> {
                updateLanguageSetting(requireContext(), SETTING_ENGLISH)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    companion object {
        fun newInstance() = AnimeFragment()
    }
}