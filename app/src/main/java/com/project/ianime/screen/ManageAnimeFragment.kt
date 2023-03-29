package com.project.ianime.screen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.project.ianime.root.BaseFragment
import com.project.ianime.screen.stateholder.ManageAnimeUiState
import com.project.ianime.utils.image.ImageStub
import com.project.ianime.utils.showExitBeforeDialog
import java.io.IOException

abstract class ManageAnimeFragment : BaseFragment<ManageAnimeUiState>(ManageAnimeUiState::class.java) {

    private val imageStub = ImageStub()
    private lateinit var selectedImageBitmap: Bitmap
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        uiState.addProfileButton.setOnClickListener {
            openGallery()
        }

        uiState.saveButton.setOnClickListener {
            uiState.startLoadingState()
//            saveAnime()
        }
    }

    abstract fun saveAnime()

    private fun init(){
        uiState.saveButton.isEnabled = false
    }

    override fun navigateBack(): Boolean {
        return if (uiState.navigateBackState()) {
            appNavigation.closeTopFragment()
        } else {
            showExitBeforeDialog(requireContext(), appNavigation)
            true
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        try {
            uploadProfileActivity.launch(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    private val uploadProfileActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null && data.data != null) {
                    val selectedImageUri: Uri? = data.data
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                        requireActivity().contentResolver,
                        selectedImageUri
                    )
                    try {
                        uiState.profilePreview.setImageURI(selectedImageUri)
                        uiState.pictureSelectedState = true
                        uiState.activateSaveButton()
                        // Used for debug purpose
                        Log.i(IMAGE_BMP, "Bitmap is: " + selectedImageBitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    companion object{
        const val IMAGE_BMP = "image_bmp"
    }
}