package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.MockGenerator
import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import com.josuearevalodev.domain.repository.SetListFmRepository
import io.reactivex.Completable
import io.reactivex.Single

class SetListFmRepositoryImpl(
    private val databaseDS: SetListFmDataSource,
    private val remoteDS: SetListFmDataSource
) : SetListFmRepository {

    override fun getArtist(artistName: String): Single<ArtistEntity> {

        // Remote + insert
        /*return remoteDS
            .getArtist(artistName)
            .doOnSuccess { insertArtistInDatabase(it)
                .subscribe { System.out.println("TEST - Completed!") }}*/

        // Concat
        /*return Single.concatArray(
            databaseDS.getArtists(artistName),
            remoteDS.getArtists(artistName)
        ).firstOrError()

        // DB only
        return databaseDS.getArtist(artistName)

        // Remote only
        //return remoteDS.getArtist(artistName)

        // Mock
        //return Single.just(MockGenerator.searchArtistsResponse)

    }

    override fun getArtistSetlists(
        artistId: String,
        page: Int
    ): Single<ArtistSetlistsResponse> {
        //return Single.just(MockGenerator.artistSetlistsResponse)
        return remoteDS.getArtistSetlists(artistId, page)
    }

    fun insertArtistInDatabase(artist: ArtistEntity): Completable {
        return databaseDS.insertArtist(artist)
    }
}
