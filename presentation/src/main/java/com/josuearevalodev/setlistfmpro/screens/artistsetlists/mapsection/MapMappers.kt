package com.josuearevalodev.setlistfmpro.screens.artistsetlists.mapsection

import android.graphics.Color
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity

val SetlistEntity.mapToSetlistMarker: CustomMarker.SetlistMarker
    get() = CustomMarker.SetlistMarker(
        title = "${venue.name} - ${venue.city.name}\n $eventDate",
        location = MarkerLocation(
            latitude = venue.city.coords.lat,
            longitude = venue.city.coords.long
        )
    )

fun String.mapToBitmapDescriptor(): BitmapDescriptor {
    val hsv = FloatArray(3)
    Color.colorToHSV(Color.parseColor(this), hsv)
    return BitmapDescriptorFactory.defaultMarker(hsv[0])
}

