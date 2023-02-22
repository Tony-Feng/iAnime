package com.project.ianime.root

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.R
import com.project.ianime.navigation.NavigationManagerImpl
import com.project.ianime.navigation.NavigationManagerLifecycle
import com.project.ianime.utils.updateLanguageSetting

abstract class BaseContainerActivity<VH: ViewHolder>(containerViewHolder: VH): AppCompatActivity(){
    val containerViewHolder = containerViewHolder
    private val navigationManagerLifecycle: NavigationManagerLifecycle
        get() {
            return NavigationManagerImpl(supportFragmentManager)
        }
    constructor(vhClass: Class<VH>): this(ViewHolder.createViewHolder(vhClass))
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