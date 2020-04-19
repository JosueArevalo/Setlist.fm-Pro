package com.josuearevalodev.persistance.db

import androidx.room.Dao
import androidx.room.Query
import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.CacheArtistEntity
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single

@Dao
interface SetlistFmDao {
    @Query("SELECT * FROM artists")
    fun getArtists(artistName: String): Single<List<CacheArtistEntity>>

    //fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}