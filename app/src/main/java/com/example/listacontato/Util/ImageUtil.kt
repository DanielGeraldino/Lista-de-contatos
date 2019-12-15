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

fun transformaUriEmByteArray(uri: Uri, context: Context): ByteArray {
    return context.contentResolver.openInputStream(uri)!!.buffered()!!.use { it.readBytes() }
}



fun transformaByteArrayEmBitmap(imageArray: ByteArray): Bitmap{
    return BitmapFactory.decodeByteArray(imageArray,0, imageArray.size)
}