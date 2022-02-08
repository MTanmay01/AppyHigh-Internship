package com.mtanmay.appyhighinternship.utils

import android.content.Context
import android.location.LocationManager

class GpsUtils {

    companion object {

        fun isEnabled(context: Context): Boolean {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

    }

}