package com.project.ianime.screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.project.ianime.R
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.viewholder.AnimeViewHolder
import com.project.ianime.utils.updateLanguageSetting

class AnimeFragment : BaseFragment<AnimeViewHolder>(AnimeViewHolder::class.java) {
    override fun updateActionBar(): Boolean {
        //TODO: Update to each anime name
        actionBarService.setTitle(getString(R.string.app_name), viewHolder.toolbar)
        actionBarService.setNavigateBackAction(viewHolder.toolbar, this)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Test using mocked data
        getAnimeDetails()
    }
    //TODO: Update using Get API
    private fun getAnimeDetails(){
        viewHolder.animeProfile.setImageResource(R.drawable.ic_gallery)
        viewHolder.animeName.text = "Throne of Seal"
        viewHolder.animeCountry.text = getString(R.string.anime_country_title, "China")
        viewHolder.animeType.text = getString(R.string.anime_type_title, "God")
        viewHolder.animePublishedYear.text = getString(R.string.anime_year_title, "2021")
        viewHolder.animeStatus.text = getString(R.string.anime_status_title, "In Progress")
        viewHolder.animeIntro.text = "A boy on his own way to fight"

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit_anime -> {
                navigationManager.showFragmentOverTop(
                    EditAnimeFragment.newInstance(),
                    baseContainerId
                )
                true
            }
            R.id.setting_chinese_language -> {
                updateLanguageSetting(requireContext(), BaseContainerActivity.SETTING_CHINESE)
                true
            }
            R.id.setting_english_language -> {
                updateLanguageSetting(requireContext(), BaseContainerActivity.SETTING_ENGLISH)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    companion object {
        fun newInstance() = AnimeFragment()
    }
}