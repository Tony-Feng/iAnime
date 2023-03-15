package com.project.ianime.screen.stateholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.project.ianime.databinding.FragmentManageAnimeBinding
import com.project.ianime.root.FragmentUiState
import com.project.ianime.widget.setShowProgress
import com.project.ianime.widget.setTextChangeListener
import io.reactivex.rxjava3.core.Observable

class ManageAnimeUiState : FragmentUiState() {
    private var _binding: FragmentManageAnimeBinding? = null
    val binding get() = _binding!!
    lateinit var toolbar: Toolbar
    lateinit var addProfileButton: Button
    lateinit var profilePreview: ImageView
    lateinit var animeChineseName: TextInputLayout
    lateinit var animeEnglishName: TextInputLayout
    lateinit var animeRate: TextInputLayout
    lateinit var animeYear: TextInputLayout
    lateinit var animeDescription: TextInputLayout
    lateinit var animeCountry: TextInputLayout
    lateinit var animeType: TextInputLayout
    lateinit var animeStatus: TextInputLayout
    lateinit var saveButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageAnimeBinding.inflate(inflater, container, false)
        toolbar = binding.topAppBar.toolBar

        addProfileButton = binding.addCoverButton
        profilePreview = binding.profilePreview
        animeChineseName = binding.manageChineseName
        animeEnglishName = binding.manageEnglishName
        animeRate = binding.manageRate
        animeYear = binding.manageYear
        animeDescription = binding.manageDescription
        animeCountry = binding.manageCountry
        animeType = binding.manageType
        animeStatus = binding.manageStatus
        saveButton = binding.buttonSave

        //TODO 2023-03-15: Add change listener for image
        Observable.combineLatest(
            listOf(
                animeChineseName.setTextChangeListener(),
                animeEnglishName.setTextChangeListener(),
                animeRate.setTextChangeListener(),
                animeYear.setTextChangeListener(),
                animeDescription.setTextChangeListener(),
                animeCountry.setTextChangeListener(),
                animeType.setTextChangeListener(),
                animeStatus.setTextChangeListener()
            )
        ) { values ->
            values.forEach {
                if (!(it as Boolean)) {
                    return@combineLatest false
                }
            }
            return@combineLatest true
        }
            .distinctUntilChanged { _, current -> current == saveButton.isEnabled }
            .subscribe { saveButton.isEnabled = true }
            .also { compositeDisposable.add(it) }

        return binding.root
    }

    fun navigateBackState(): Boolean {
        return !saveButton.isEnabled
    }

    fun startLoadingState() {
        saveButton.setShowProgress(true)
    }

    fun stopLoadingState() {
        saveButton.setShowProgress(false)
    }

    override fun onDestroyView() {
        _binding = null
        compositeDisposable.clear()
    }
}