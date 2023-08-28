package com.project.ianime.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.project.ianime.R

/**
 * Method used in menu for change app language
 */
fun updateLanguageSetting(context: Context, language: String) {
    AppCompatDelegate.setApplicationLocales(
        LocaleListCompat.forLanguageTags(language)
    )
    when (language) {
        Constants.LANG_ZH -> Toast.makeText(context, R.string.change_zh_message, Toast.LENGTH_SHORT).show()
        Constants.LANG_EN -> Toast.makeText(context, R.string.change_en_message, Toast.LENGTH_SHORT).show()
    }
}