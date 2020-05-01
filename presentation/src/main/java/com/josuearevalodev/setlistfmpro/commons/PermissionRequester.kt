package com.josuearevalodev.setlistfmpro.commons

import android.app.Application
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.single.PermissionListener

class PermissionRequester(private val application: Application) {
    fun request(permission: String,
                permissionListener: PermissionListener,
                permissionRequestErrorListener: PermissionRequestErrorListener) {

        Dexter.withContext(application)
            .withPermission(permission)
            .withListener(permissionListener)
            .withErrorListener(permissionRequestErrorListener)
            .check()
    }
}
