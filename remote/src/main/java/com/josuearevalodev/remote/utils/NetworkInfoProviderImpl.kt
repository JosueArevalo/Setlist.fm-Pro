package com.josuearevalodev.remote.utils

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress
import java.net.UnknownHostException

class NetworkInfoProviderImpl(
    private val context: Context) : NetworkInfoProvider {

    override fun hasInternetConnection(): Boolean {
        return isNetworkAvailable(context) && isInternetAvailable()
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val address: InetAddress = InetAddress.getByName("www.setlist.fm")
            address.toString().isNotEmpty()
        } catch (e: UnknownHostException) { // Log error
            false
        }
    }

}
