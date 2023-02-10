package com.project.ianime

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.navigation.NavigationManager
import com.project.ianime.navigation.NavigationManagerImpl
import com.project.ianime.utils.updateLanguageSetting

class HomeActivity : AppCompatActivity() {

    private val navigationManager: NavigationManager
    init {
        val navigationManagerImpl = NavigationManagerImpl(
            supportFragmentManager
        )
        navigationManager = navigationManagerImpl
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Activate Setting Menu
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolBar)
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

    companion object{
        const val SETTING_CHINESE = "zh"
        const val SETTING_ENGLISH = "en"
    }
}