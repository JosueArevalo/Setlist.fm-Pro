package com.josuearevalodev.setlistfmpro.screens.setlistdetail

import android.content.Context
import android.content.Intent

//private const val EXTRA_ARTIST_NAME = "EXTRA_ARTIST_NAME"
private const val EXTRA_ARTIST_ID = "EXTRA_ARTIST_ID"
private const val EXTRA_SETLIST_ID = "EXTRA_SETLIST_ID"

fun Context.navigateToSetlistDetail(artistId: String, setlistId: String) {
    val intent = Intent(this, SetlistDetailActivity::class.java).apply {
        putExtra(EXTRA_ARTIST_ID, artistId)
        putExtra(EXTRA_SETLIST_ID, setlistId)
    }

    startActivity(intent)
}

val SetlistDetailActivity.getArtistIdFromIntent
        get() = intent.getStringExtra(EXTRA_ARTIST_ID)

val SetlistDetailActivity.getSetlistIdFromIntent
    get() = intent.getStringExtra(EXTRA_SETLIST_ID)
