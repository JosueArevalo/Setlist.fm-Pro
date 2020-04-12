package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.content.Context
import android.content.Intent

private val EXTRA_ARTIST_NAME = "EXTRA_ARTIST_NAME"

fun Context.navigateToArtistSetlists(artistName: String) {
    val intent = Intent(this, ArtistSetlistsActivity::class.java).apply {
        putExtra(EXTRA_ARTIST_NAME, artistName)
    }

    startActivity(intent)
}

fun ArtistSetlistsActivity.getArtistNameFromIntent() = intent.getStringExtra(EXTRA_ARTIST_NAME)
