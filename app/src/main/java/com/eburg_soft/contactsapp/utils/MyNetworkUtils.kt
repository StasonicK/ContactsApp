package com.eburg_soft.contactsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class MyNetworkUtils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
//            val connectivityManager: ConnectivityManager =
//                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                    ?: return false
//            val networkInfo = connectivityManager.activeNetworkInfo
//            return networkInfo.isConnected

            try {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val activeNetwork = connectivityManager.activeNetwork ?: return false
                    val networkCapabilities =
                        connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                } else {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return false
                    activeNetworkInfo.isConnected && (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI || activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }
        }
    }