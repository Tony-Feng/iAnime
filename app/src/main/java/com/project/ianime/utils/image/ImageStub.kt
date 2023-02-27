package com.project.ianime.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageStub {
    /**
     * Get encoded image bitmap with Base-64
     * @param [bitmap] - bitmap of current upload image
     */
    fun getEncodedSelectedImageBitmap(bitmap: Bitmap): String{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val bytes = outputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * Get decoded image bitmap with Base-64
     * @param [encodedImageString] - string of encoded bitmap
     */
    fun getDecodedSelectedImageBitmap(encodedImageString: String): Bitmap{
        val decodedBytes = Base64.decode(encodedImageString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}