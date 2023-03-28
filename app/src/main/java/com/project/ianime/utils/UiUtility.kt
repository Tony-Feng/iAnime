package com.project.ianime.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.project.ianime.R
import com.project.ianime.navigation.AppNavigation

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