package com.josuearevalodev.persistence.db

import androidx.room.Dao
import androidx.room.Query
import com.josuearevalodev.domain.entities.DatabaseArtistEntity
import io.reactivex.Single

@Dao
interface SetlistFmDao {
    @Query("SELECT * FROM artists WHERE name = :artistName")
    fun getArtists(artistName: String): Single<DatabaseArtistEntity>

    //fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}