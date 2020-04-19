package com.josuearevalodev.persistence.db

import androidx.room.Dao
import androidx.room.Query
import com.josuearevalodev.domain.entities.CacheArtistEntity
import io.reactivex.Single

@Dao
interface SetlistFmDao {
    @Query("SELECT * FROM artists")
    fun getArtists(artistName: String): Single<List<CacheArtistEntity>>

    //fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}