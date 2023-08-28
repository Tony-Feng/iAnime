package com.project.ianime.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.ianime.repository.AnimeDataRepository
import javax.inject.Inject

/**
 * View model factory to instantiate the view model
 */
class AnimeViewModelFactory @Inject constructor(private val repository: AnimeDataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(repository) as T
    }
}