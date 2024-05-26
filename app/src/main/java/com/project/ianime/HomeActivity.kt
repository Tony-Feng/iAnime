package com.project.ianime

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.project.ianime.di.AnimeApplication
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.screens.home.HomeFragment
import com.project.ianime.utils.Constants
import com.project.ianime.utils.updateLanguageSetting
import com.project.ianime.viewmodels.AnimeViewModel
import com.project.ianime.viewmodels.AnimeViewModelFactory
import javax.inject.Inject

/**
 * HomeActivity is the entry of the app
 */
class HomeActivity : BaseContainerActivity() {

    @Inject
    lateinit var animeViewModelFactory: AnimeViewModelFactory

    private lateinit var animeViewModel: AnimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // instantiate a shared view model used across fragments within activity
        (application as AnimeApplication).applicationComponent.inject(this)
        animeViewModel = ViewModelProvider(this, animeViewModelFactory).get(AnimeViewModel::class.java)
        navigateToHome()
    }

    private fun navigateToHome() {
        supportFragmentManager.beginTransaction()
            .replace(containerViewHolderId, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting_chinese -> {
                updateLanguageSetting(this, Constants.LANG_ZH)
                true
            }
            R.id.setting_english -> {
                updateLanguageSetting(this, Constants.LANG_EN)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}