package com.josuearevalodev.persistence.db

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.josuearevalodev.domain.entities.DatabaseArtistEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SetlistFmDao {
    @Query("SELECT * FROM artists WHERE name = :artistName")
    fun getArtist(artistName: String): Single<DatabaseArtistEntity>

    //fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>

    @Insert(onConflict = IGNORE)
    fun insertArtist(artist: DatabaseArtistEntity): Completable
}
