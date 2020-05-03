package com.josuearevalodev.persistence.setlistfm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.josuearevalodev.persistence.setlistfm.entities.DatabaseArtistEntity
import com.josuearevalodev.persistence.setlistfm.entities.DatabaseSetlistEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SetlistFmDao {
    @Query("SELECT * FROM artists WHERE name = :artistName")
    fun getArtist(artistName: String): Single<DatabaseArtistEntity>

    @Query("SELECT * FROM setlists WHERE artistId = :artistId")
    fun getArtistSetlists(artistId: String/*TODO:, page: Int*/): Single<List<DatabaseSetlistEntity>>

    @Query("SELECT * FROM setlists WHERE id = :setlistId")
    fun getSetlist(setlistId: String): Single<DatabaseSetlistEntity>

    @Insert(onConflict = IGNORE)
    fun insertArtist(artist: DatabaseArtistEntity): Completable

    @Insert(onConflict = IGNORE)
    fun insertSetlists(setlists: List<DatabaseSetlistEntity>): Completable

    @Query("UPDATE artists SET itemsPerPage = :itemsPerPage, totalSetlists = :totalSetlists WHERE mbid = :idArtist")
    fun updateArtistWithSetlistsHeaderData(idArtist: String, itemsPerPage: Int, totalSetlists: Int): Completable
}
