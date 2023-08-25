package com.project.ianime.screens.stateholder

import com.project.ianime.api.error.ErrorType

/**
 * State holder for setting screen state for 'Success', 'Error', and 'Empty'
 */
sealed class AnimeUiState {

    object Success : AnimeUiState()

    object Empty : AnimeUiState()

    data class Error(val errorType: ErrorType) : AnimeUiState()
}
