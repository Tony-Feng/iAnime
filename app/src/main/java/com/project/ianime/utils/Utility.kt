package com.project.ianime.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.project.ianime.R
import com.project.ianime.navigation.AppNavigation

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

fun showExitBeforeDialog(context: Context, appNavigation: AppNavigation) {
    AlertDialog.Builder(context)
        .setMessage(R.string.exit_dialog_message)
        .setCancelable(true)
        .setPositiveButton(
            R.string.positive_button_label
        ) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        .setNegativeButton(R.string.negative_button_label) { _, _ ->
            appNavigation.closeTopFragment()
        }
        .show()
}