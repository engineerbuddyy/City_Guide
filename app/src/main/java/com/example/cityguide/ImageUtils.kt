package com.example.cityguide


import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object ImageUtils {
    fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val file = File(context.filesDir, "place_${System.currentTimeMillis()}.jpg")
            inputStream.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
            file.absolutePath  // ✅ store safe internal path
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
