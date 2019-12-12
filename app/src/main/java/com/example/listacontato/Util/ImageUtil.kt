package com.example.listacontato.Util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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

fun transformaByteArrayEmUri(imageArray: ByteArray) : Uri{

    val uri = Uri.parse(imageArray.toString())

    return uri
}