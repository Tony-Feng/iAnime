package com.project.ianime.screen

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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