package com.josuearevalodev.remote.setlistfm.entities

data class RemoteSetEntity(
    val name: String?,
    val encore: Int?,
    val song: List<RemoteSongEntity>?
)

