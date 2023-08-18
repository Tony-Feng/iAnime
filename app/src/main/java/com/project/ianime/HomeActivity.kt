package com.project.ianime

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.project.ianime.root.BaseContainerActivity
import com.project.ianime.screens.home.HomeFragment
import com.project.ianime.utils.updateLanguageSetting

/**
 * HomeActivity is the start of the App
 */
class HomeActivity : BaseContainerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigateToHome()
    }

    private fun navigateToHome() {
        supportFragmentManager.beginTransaction()
            .replace(containerViewHolderId, HomeFragment.newInstance())
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
            R.id.setting_chinese_language -> {
                updateLanguageSetting(this, SETTING_CHINESE)
                true
            }
            R.id.setting_english_language -> {
                updateLanguageSetting(this, SETTING_ENGLISH)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    companion object {
        const val SETTING_CHINESE = "zh"
        const val SETTING_ENGLISH = "en"
    }

}