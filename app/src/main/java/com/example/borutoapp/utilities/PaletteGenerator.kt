package com.example.borutoapp.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult
import okhttp3.internal.toHexString

object PaletteGenerator {

    suspend fun convertUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val imageRequest = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val imageLoader = ImageLoader(context = context)
        val imageResult = imageLoader.execute(request = imageRequest)
        return if (imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorFromBitmap(bitmap: Bitmap): Map<String, String> {
        return mapOf(
            "vibrant" to parseStringFromSwatch(Palette.from(bitmap).generate().vibrantSwatch),
            "darkVibrant" to parseStringFromSwatch(
                Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            "onDarkVibrant" to parseStringFromInt(
                Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            ),
            "lightVibrant" to parseStringFromSwatch(
                Palette.from(bitmap).generate().lightVibrantSwatch
            )
        )
    }

    private fun parseStringFromSwatch(swatch: Swatch?): String {
        return if (swatch != null) {
            "#" + Integer.toHexString(swatch.rgb)
        } else {
            "#000000"
        }
    }

    private fun parseStringFromInt(swatch: Int?): String {
        return if (swatch != null) {
            "#" + Integer.toHexString(swatch)
        } else {
            "#FFFFFF"
        }
    }
}