package com.project.ianime.screen.stateholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.project.ianime.databinding.FragmentManageAnimeBinding
import com.project.ianime.root.FragmentUiState
import com.project.ianime.widget.AnimeEditText
import com.project.ianime.widget.setShowProgress
import io.reactivex.rxjava3.core.Observable

class ManageAnimeUiState : FragmentUiState() {
    private var _binding: FragmentManageAnimeBinding? = null
    val binding get() = _binding!!
    lateinit var toolbar: Toolbar
    lateinit var addProfileButton: Button
    lateinit var profilePreview: ImageView
    lateinit var animeChineseName: AnimeEditText
    lateinit var animeEnglishName: AnimeEditText
    lateinit var animeRate: AnimeEditText
    lateinit var animeYear: AnimeEditText
    lateinit var animeDescription: AnimeEditText
    lateinit var animeCountry: AnimeEditText
    lateinit var animeType: AnimeEditText
    lateinit var animeStatus: AnimeEditText
    lateinit var saveButton: MaterialButton
    var pictureSelectedState: Boolean = false
    var formEnteredState: Boolean = false

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
            .subscribe { isValid ->
                formEnteredState = isValid
                activateSaveButton()
            }
            .also { compositeDisposable.add(it) }

        return binding.root
    }

    fun activateSaveButton(){
        saveButton.isEnabled = formEnteredState && pictureSelectedState
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