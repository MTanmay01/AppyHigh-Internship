package com.mtanmay.appyhighinternship.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil {

    companion object {

        fun isConnected(context: Context): Boolean {

            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

            val connMobile = infoMobile?.state == NetworkInfo.State.CONNECTED
            val connWifi = infoWifi?.state == NetworkInfo.State.CONNECTED

            return connMobile || connWifi

        }

    }

}