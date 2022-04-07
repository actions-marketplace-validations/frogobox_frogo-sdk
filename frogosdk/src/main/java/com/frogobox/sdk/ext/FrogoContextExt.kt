package com.frogobox.sdk.ext

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.pm.PackageInfoCompat
import com.frogobox.log.FLog


/*
 * Created by faisalamir on 07/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

private const val TAG = "FrogoContextExt"

// -------------------------------------------------------------------------------------------------

fun Context.getAppVersionCode(): Int? {
    try {
        return packageManager?.getPackageInfo(packageName, 0)
            ?.let { PackageInfoCompat.getLongVersionCode(it).toInt() }
    } catch (ex: Exception) {
        FLog.e("$TAG : ${ex.message.orEmpty()}")
    }

    return null
}

// -------------------------------------------------------------------------------------------------

fun Context.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, message, duration).show()
}

// -------------------------------------------------------------------------------------------------

fun Context.hasCameraPermission(): Boolean =
    ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

// -------------------------------------------------------------------------------------------------

fun Context.hasReadExtStoragePermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasWriteExtStoragePermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

// -------------------------------------------------------------------------------------------------