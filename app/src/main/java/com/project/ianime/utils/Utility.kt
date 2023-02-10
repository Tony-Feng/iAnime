package com.project.ianime.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

fun updateLanguageSetting(context: Context, language: String) {
    AppCompatDelegate.setApplicationLocales(
        LocaleListCompat.forLanguageTags(language)
    )
    when (language) {
        "zh" -> Toast.makeText(context, "App language changed to Chinese", Toast.LENGTH_SHORT)
            .show()
        "en" -> Toast.makeText(context, "App language changed to English", Toast.LENGTH_SHORT)
            .show()
    }
}