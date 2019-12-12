package com.example.listacontato.Util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import java.io.ByteArrayOutputStream


fun transformaUriEmByteArray(uri: Uri, context: Context) : ByteArray {

    val outputStream = ByteArrayOutputStream()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        val bitmap: Bitmap = ImageDecoder.decodeBitmap(source)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream)
    }

    return outputStream.toByteArray()
}

fun transformaByteArrayEmBitmap(imageArray: ByteArray) : Bitmap{

    val bitmap = BitmapFactory.decodeByteArray(imageArray,0, imageArray.size)

    return bitmap

}