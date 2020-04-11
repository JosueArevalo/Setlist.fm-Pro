package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.MockGenerator
import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import com.josuearevalodev.domain.repository.SetListFmRepository
import io.reactivex.Single

class SetListFmRepositoryImpl(private val remoteDS: SetListFmDataSource) : SetListFmRepository {

    override fun getArtists(artistName: String): Single<SearchArtistsResponse> {
        //return Single.just(MockGenerator.searchArtistsResponse)
        return remoteDS.getArtists(artistName)
    }

    override fun getArtistSetlists(
        artistId: String,
        page: Int
    ): Single<ArtistSetlistsResponse> {
        //return Single.just(MockGenerator.artistSetlistsResponse)
        return remoteDS.getArtistSetlists(artistId, page)
    }
}