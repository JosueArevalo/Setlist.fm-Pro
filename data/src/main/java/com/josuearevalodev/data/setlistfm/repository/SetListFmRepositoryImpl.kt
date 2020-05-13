package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.data.setlistfm.error.DatabaseError
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit

class SetListFmRepositoryImpl(
    private val databaseDS: SetListFmDatabaseDataSource,
    private val remoteDS: SetListFmRemoteDataSource
) : SetListFmRepository {

    override fun getArtist(artistName: String): Single<ArtistEntity> {
        return handleGetArtist(artistName)
    }

    override fun getArtistSetlists(
        artistId: String,
        page: Int,
        itemsPerPage: Int
    ): Single<ArtistSetlistsResponseEntity> {
        return handleGetArtistSetlists(artistId, page, itemsPerPage)
    }

    override fun getSetlistDetail(setlistId: String): Single<SetlistEntity> {
        return databaseDS.getSetlistDetail(setlistId)
    }

    override fun updateArtistWithSetlistsHeaderData(idArtist: String, itemsPerPage: Int, totalSetlists: Int, lastPage1RemoteCall: Long): Completable {
        return when (lastPage1RemoteCall) {
            -1L -> databaseDS.updateArtistWithSetlistsHeaderData(
                idArtist = idArtist,
                itemsPerPage = itemsPerPage,
                totalSetlists = totalSetlists
            )
            else -> {
                databaseDS.updateArtistWithSetlistsHeaderData(
                    idArtist = idArtist,
                    itemsPerPage = itemsPerPage,
                    totalSetlists = totalSetlists,
                    lastPage1RemoteCall = lastPage1RemoteCall
                )
            }
        }
    }

    //==============================================================================================
    //region getArtist - private methods
    //==============================================================================================

    private fun handleGetArtist(artistName: String): Single<ArtistEntity> {
        return getArtistFromDbWithName(artistName)
            .onErrorResumeNext { error ->
                System.out.println("TEST - Error getting from DB: $error")
                handleGetArtistDbError(error, artistName)
            }
    }

    private fun getArtistFromDbWithName(artistName: String): Single<ArtistEntity> {
        return databaseDS
            .getArtist(artistName)
    }

    private fun getArtistFromDbWithId(artistId: String): Single<ArtistEntity> {
        return databaseDS
            .getArtistWithId(artistId = artistId)
    }

    private fun handleGetArtistDbError(error: Throwable, artistName: String): Single<ArtistEntity> {
        // For the moment, either NoResultsFound or other, I do a remote call
        return when (error) {
            is DatabaseError.NoResultsFound -> getArtistFromRemote(artistName)
                .flatMap { getArtistFromDbWithName(artistName) }
            else -> getArtistFromRemote(artistName)
                .flatMap { artistEntity ->
                    getArtistFromDbWithId(artistEntity.mbid)
                }
        }
    }

    private fun getArtistFromRemote(artistName: String): Single<ArtistEntity> {
        // NOTE: Due a limitation of current api key for 2 requests per second,
        // I wait 1 second betweeen search artists & getSetlists, to avoid to get 429 http error
        return remoteDS
            .getArtist(artistName)
            .delay(1000, TimeUnit.MILLISECONDS)
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

    private fun handleGetArtistSetlists(artistId: String, page: Int, itemsPerPage: Int): Single<ArtistSetlistsResponseEntity> {
        return getSetlistsFromDb(artistId, page, itemsPerPage)
            .onErrorResumeNext { error ->
                System.out.println("TEST - Error getting from DB: $error")
                handleGetSetlistsDbError(error, artistId, page, itemsPerPage)
            }.flatMap { setlists ->
                databaseDS.getArtistWithId(artistId = artistId)
                    .map { artist ->
                        ArtistSetlistsResponseEntity(
                            type = "",
                            itemsPerPage = artist.itemsPerPage,
                            page = page,
                            total = artist.totalSetlists,
                            setlist = setlists
                        )
                    }
            }
    }

    private fun getSetlistsFromDb(artistId: String, page: Int, itemsPerPage: Int): Single<List<SetlistEntity>> {
        return databaseDS
            .getArtistSetlists(artistId, page, itemsPerPage)
    }

    private fun handleGetSetlistsDbError(error: Throwable, artistId: String, page: Int, itemsPerPage: Int): Single<List<SetlistEntity>> {
        // For the moment, either NoResultsFound or other, I do a remote call
        return when (error) {
            is DatabaseError.NoResultsFound -> getSetlistsFromRemote(artistId, page)
                .flatMap { getSetlistsFromDb(artistId, page, itemsPerPage) }
            else -> getSetlistsFromRemote(artistId, page)
                .flatMap { getSetlistsFromDb(artistId, page, itemsPerPage) }
        }
    }

    private fun getSetlistsFromRemote(artistId: String, page: Int): Single<ArtistSetlistsResponseEntity> {
        return remoteDS
            .getArtistSetlists(artistId, page)
            .doOnSuccess { artistSetlistsResponse ->
                // Setlists inserted in DB (setlists table)
                insertSetlistsInDatabase(artistSetlistsResponse.setlist)
                    .subscribe { System.out.println("TEST - Remote item inserted in DB!") }

                // Header data updated in DB (artists table)
                // If this remote call is page is #1, we save timestamp in DB
                // If is not page #1, we don't save anything, because we only want to know
                // newest setlists.
                val updateValuesCompletable = when (page) {
                    1 -> {
                        updateArtistWithSetlistsHeaderData(
                            idArtist = artistId,
                            itemsPerPage = artistSetlistsResponse.itemsPerPage,
                            totalSetlists = artistSetlistsResponse.total,
                            lastPage1RemoteCall = Date().time
                        )
                    }
                    else -> {
                        updateArtistWithSetlistsHeaderData(
                            idArtist = artistId,
                            itemsPerPage = artistSetlistsResponse.itemsPerPage,
                            totalSetlists = artistSetlistsResponse.total
                        )
                    }
                }.apply {
                    subscribe { System.out.println("TEST - Header values updated in DB!") }
                }
            }
    }

    private fun insertSetlistsInDatabase(setlists: List<SetlistEntity>): Completable {
        return databaseDS.insertSetlists(setlists)
    }

    //endregion
}
