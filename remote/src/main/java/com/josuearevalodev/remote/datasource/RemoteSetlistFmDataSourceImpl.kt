package com.josuearevalodev.remote.datasource

import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import com.josuearevalodev.remote.service.SetlistFmService
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.mapper.mapToArtistSetlistsResponse
import com.josuearevalodev.remote.mapper.mapToSearchArtistsResponse
import io.reactivex.Single

class RemoteSetlistFmDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : RemoteSetlistFmDataSource {

    override fun getArtists(artistName: String): Single<SearchArtistsResponse> {
        return httpClient.create(SetlistFmService::class.java, baseUrl)
            .getArtists(artistName)
            .map { it.mapToSearchArtistsResponse }
    }

    override fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse> {
        return httpClient.create(SetlistFmService::class.java, baseUrl)
            .getArtistSetlists(artistId, page)
            .map { it.mapToArtistSetlistsResponse }
    }
}
