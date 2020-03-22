package com.eburg_soft.contactsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class MyNetworkUtils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            var connectivityManager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager == null) {
                return false
            }
            val networkInfo: NetworkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}