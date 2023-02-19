package com.project.ianime.root

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.project.ianime.R
import com.project.ianime.actionbar.ActionBarService
import com.project.ianime.actionbar.ActionBarServiceImpl
import com.project.ianime.navigation.NavigationManager
import com.project.ianime.navigation.NavigationManagerImpl
import com.project.ianime.utils.updateLanguageSetting

abstract class BaseContainerActivity<VH: ContainerViewHolder>(containerViewHolder: VH): AppCompatActivity(){

    val navigationManager: NavigationManager
    val viewHolder: VH = containerViewHolder
    val actionBar: ActionBarService = ActionBarServiceImpl()

    init {
        val navigationManagerImpl = NavigationManagerImpl(
            supportFragmentManager,
            viewHolder
        )
        navigationManager = navigationManagerImpl
    }

    constructor(vhClass: Class<VH>): this(ContainerViewHolder.createContainerViewHolder(vhClass))

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