package com.project.ianime.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import com.project.ianime.R
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class ImageUtils {

    /**
     * Get encoded image bitmap with Base-64
     * @param [bitmap] - bitmap of current upload image
     */
    fun getEncodedSelectedImageBitmap(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val bytes = outputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * Get decoded image bitmap with Base-64
     * @param [encodedImageString] - string of encoded bitmap
     */
    fun getDecodedSelectedImageBitmap(encodedImageString: String): Bitmap {
        val decodedBytes = Base64.decode(encodedImageString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    /**
     * Lazy loading image with a given url from disk, if disk cache is not existed, load image from online again
     * with 'memory policy' specified to save image directly to disk
     * @param imageUrl - the URL of the image
     * @param imageView - the target to load image into
     */
    fun loadImageFromDisk(imageUrl: String, imageView: ImageView) {
        Picasso.get()
            .load(imageUrl)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .placeholder(R.drawable.ic_anime_placeholder)
            .into(imageView, object : Callback {
                // Load image from disk cache directly
                override fun onSuccess() {
                    Picasso.get().load(imageUrl).resize(200,150).centerInside().into(imageView)
                }

                override fun onError(e: Exception?) {
                    // Try again to load image online if cache failed
                    Picasso.get()
                        .load(imageUrl)
                        .resize(200,150)
                        .centerInside()
                        .placeholder(R.drawable.ic_anime_placeholder)
                        .into(imageView, object : Callback {
                            override fun onSuccess() {
                                Log.i("Picasso", "Image saved in Cache successfully")
                            }

                            override fun onError(e: java.lang.Exception?) {
                                Log.i("Picasso", "Could not fetch image from $imageUrl")
                            }
                        })
                }
            })
    }
}