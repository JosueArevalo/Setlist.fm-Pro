package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity

@Entity
data class DatabaseCountryEntity(
    val code: String?,
    val name: String?
)
