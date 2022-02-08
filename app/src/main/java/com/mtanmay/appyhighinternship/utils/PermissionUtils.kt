package com.mtanmay.appyhighinternship.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import com.vmadalin.easypermissions.EasyPermissions

class PermissionUtils {

    companion object {

        fun hasLocationPermission(context: Context): Boolean =
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

        fun requestLocationPermission(context: Activity) {
            EasyPermissions.requestPermissions(
                context,
                "This app need Location Permissions to work properly",
                1,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }

    }
}