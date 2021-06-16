package com.fizhu.androidplayground.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

/**
 * Created by fizhu on 16 June 2021
 * https://github.com/Fizhu
 */

fun Context.toast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Activity.withPermissions(
    listPermission: List<String>,
    action: (grantedList: List<String>, deniedList: List<String>) -> Unit
) {
    Dexter.withContext(this)
        .withPermissions(listPermission)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    action.invoke(
                        report.grantedPermissionResponses.map { it.permissionName },
                        report.deniedPermissionResponses.map { it.permissionName })
                } else {
                    toast("Permission denied")
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        })
        .onSameThread()
        .check()
}