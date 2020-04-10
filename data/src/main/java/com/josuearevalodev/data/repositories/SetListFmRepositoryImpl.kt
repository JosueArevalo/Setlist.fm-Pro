package com.josuearevalodev.data.repositories

import com.josuearevalodev.base.Result
import com.josuearevalodev.base.Success
import com.josuearevalodev.data.MockGenerator
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import com.josuearevalodev.domain.repository.SetListFmRepository
import io.reactivex.Single

class SetListFmRepositoryImpl : SetListFmRepository {

    override fun getArtists(artistName: String): Single<Result<SearchArtistsResponse, Throwable>> {
        return Single.just(Success(MockGenerator.searchArtistsResponse))
    }

    override fun getArtistSetlists(
        artistId: String,
        page: Int
    ): Single<Result<ArtistSetlistsResponse, Throwable>> {
        return Single.just(Success(MockGenerator.artistSetlistsResponse))
    }
}