package com.josuearevalodev.domain.repository

import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import io.reactivex.Single
import com.josuearevalodev.base.Result
import com.josuearevalodev.domain.entities.SearchArtistsResponse

interface SetListFmRepository {

    fun getArtists(artistName: String): Single<Result<SearchArtistsResponse, Throwable>>

    fun getArtistSetlists(artistId: String, page: Int): Single<Result<ArtistSetlistsResponse, Throwable>>
}
