package com.josuearevalodev.persistence.db

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.josuearevalodev.domain.entities.DatabaseArtistEntity
import com.josuearevalodev.domain.entities.DatabaseSetlistEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SetlistFmDao {
    @Query("SELECT * FROM artists WHERE name = :artistName")
    fun getArtist(artistName: String): Single<DatabaseArtistEntity>

    @Query("SELECT * FROM setlists WHERE artistId = :artistId")
    fun getArtistSetlists(artistId: String/*TODO:, page: Int*/): Single<List<DatabaseSetlistEntity>>

    @Insert(onConflict = IGNORE)
    fun insertArtist(artist: DatabaseArtistEntity): Completable

    @Insert(onConflict = IGNORE)
    fun insertSetlists(setlists: List<DatabaseSetlistEntity>): Completable
}
