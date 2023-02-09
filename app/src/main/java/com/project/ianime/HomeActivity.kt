package com.project.ianime

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

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
        return when(item.itemId){
            R.id.setting_chinese_language -> {
                //TODO: change app language to Chinese
                Toast.makeText(this, "App language changed to Chinese", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.setting_english_language -> {
                //TODO: change app language to English
                Toast.makeText(this, "App language changed to English", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}