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
import com.project.ianime.screen.viewholder.ManageAnimeViewHolder
import com.project.ianime.utils.image.ImageStub
import com.project.ianime.widget.setShowProgress
import java.io.IOException


abstract class ManageAnimeFragment :
    BaseFragment<ManageAnimeViewHolder>(ManageAnimeViewHolder::class.java) {

    private val imageStub = ImageStub()
    private lateinit var selectedImageBitmap: Bitmap
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewHolder.addProfileButton.setOnClickListener {
            openGallery()
        }

        viewHolder.saveButton.setOnClickListener {
            viewHolder.saveButton.setShowProgress(true)
//            saveAnime()
        }
    }

    abstract fun saveAnime()

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
                        viewHolder.profilePreview.setImageURI(selectedImageUri)
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