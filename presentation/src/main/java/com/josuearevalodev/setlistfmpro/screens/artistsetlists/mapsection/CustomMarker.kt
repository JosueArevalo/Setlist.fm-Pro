package com.josuearevalodev.setlistfmpro.screens.artistsetlists.mapsection

sealed class CustomMarker(
    val title: String,
    val location: MarkerLocation,
    val color: String
) {

    class SetlistMarker(
        title: String,
        location: MarkerLocation
    ) : CustomMarker(title, location, "#00FF00") {

    }

    class UserMarker(
        title: String,
        location: MarkerLocation
    ) : CustomMarker(title, location, "#FF0000")
}

data class MarkerLocation(
    val latitude: Float,
    val longitude: Float)
