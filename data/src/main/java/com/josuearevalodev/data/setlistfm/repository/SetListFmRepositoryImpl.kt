package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.data.setlistfm.error.DatabaseError
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import io.reactivex.Completable
import io.reactivex.Single

class SetListFmRepositoryImpl(
    private val databaseDS: SetListFmDatabaseDataSource,
    private val remoteDS: SetListFmRemoteDataSource
) : SetListFmRepository {

    override fun getArtist(artistName: String): Single<ArtistEntity> {
        return handleGetArtist(artistName)
    }

    override fun getArtistSetlists(
        artistId: String,
        page: Int
    ): Single<ArtistSetlistsResponse> {
        //return handleGetArtistSetlists(artistId, page) // Valid
        return getSetlistsFromRemote(artistId, page) // For the moment, to ensure that data is OK for pagination
    }

    override fun getSetlistDetail(setlistId: String): Single<SetlistEntity> {
        return databaseDS.getSetlistDetail(setlistId)
    }

    //==============================================================================================
    //region getArtist - private methods
    //==============================================================================================

    private fun handleGetArtist(artistName: String): Single<ArtistEntity> {
        return getArtistFromDb(artistName)
            .onErrorResumeNext { error ->
                System.out.println("TEST - Error getting from DB: $error")
                handleGetArtistDbError(error, artistName)
            }
    }

    private fun getArtistFromDb(artistName: String): Single<ArtistEntity> {
        return databaseDS
            .getArtist(artistName)
    }

    private fun handleGetArtistDbError(error: Throwable, artistName: String): Single<ArtistEntity> {
        // For the moment, either NoResultsFound or other, I do a remote call
        return when (error) {
            is DatabaseError.NoResultsFound -> getArtistFromRemote(artistName)
                .flatMap { getArtistFromDb(artistName) }
            else -> getArtistFromRemote(artistName)
                .flatMap { getArtistFromDb(artistName) }
        }
    }

    private fun getArtistFromRemote(artistName: String): Single<ArtistEntity> {
        return remoteDS
            .getArtist(artistName)
            .doOnSuccess {
                insertArtistInDatabase(it)
                    .subscribe { System.out.println("TEST - Remote item inserted in DB!") }
            }
    }

    private fun insertArtistInDatabase(artist: ArtistEntity): Completable {
        return databaseDS.insertArtist(artist)
    }

    //endregion

    //==============================================================================================
    //region getArtistSetlists - private methods
    //==============================================================================================

    private fun handleGetArtistSetlists(artistId: String, page: Int): Single<List<SetlistEntity>> {
        return getSetlistsFromDb(artistId, page)
            .onErrorResumeNext { error ->
                System.out.println("TEST - Error getting from DB: $error")
                handleGetSetlistsDbError(error, artistId, page)
            }
    }

    private fun getSetlistsFromDb(artistId: String, page: Int): Single<List<SetlistEntity>> {
        return databaseDS
            .getArtistSetlists(artistId, page)
    }

    private fun handleGetSetlistsDbError(error: Throwable, artistId: String, page: Int): Single<List<SetlistEntity>> {
        // For the moment, either NoResultsFound or other, I do a remote call
        return when (error) {
            is DatabaseError.NoResultsFound -> getSetlistsFromRemote(artistId, page)
                .flatMap { getSetlistsFromDb(artistId, page) }
            else -> getSetlistsFromRemote(artistId, page)
                .flatMap { getSetlistsFromDb(artistId, page) }
        }
    }

    private fun getSetlistsFromRemote(artistId: String, page: Int): Single<ArtistSetlistsResponse> {
        return remoteDS
            .getArtistSetlists(artistId, page)
            .doOnSuccess { artistSetlistsResponse ->
                insertSetlistsInDatabase(artistSetlistsResponse.setlist)
                    .subscribe { System.out.println("TEST - Remote item inserted in DB!") }
            }
    }

    private fun insertSetlistsInDatabase(setlists: List<SetlistEntity>): Completable {
        return databaseDS.insertSetlists(setlists)
    }

    //endregion
}
