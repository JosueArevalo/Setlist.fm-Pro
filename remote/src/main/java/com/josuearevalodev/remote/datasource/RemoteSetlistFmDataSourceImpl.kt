package com.josuearevalodev.remote.datasource

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.domain.entities.*
import com.josuearevalodev.remote.error.*
import com.josuearevalodev.remote.service.SetlistFmService
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.mapper.mapToArtistEntity
import com.josuearevalodev.remote.mapper.mapToArtistSetlistsResponse
import io.reactivex.Single
import io.reactivex.SingleSource
import retrofit2.HttpException

class RemoteSetlistFmDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : SetListFmDataSource {

    override fun getArtists(artistName: String): Single<List<ArtistEntity>> {
        return httpClient.create(SetlistFmService::class.java, baseUrl)
            .getArtists(artistName)
            .onErrorResumeNext {
                (it as? HttpException)?.let { httpException ->
                    httpException.toRemoteDataError as SingleSource<RemoteSearchArtistsResponse>
                } ?: Single.error(Unexpected(it))
            }
            .map { it.artist?.map { it.mapToArtistEntity } ?: listOf() }
    }

    override fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse> {
        return httpClient.create(SetlistFmService::class.java, baseUrl)
            .getArtistSetlists(artistId, page)
            .onErrorResumeNext {
                (it as? HttpException)?.let { httpException ->
                    httpException.toRemoteDataError as SingleSource<RemoteArtistSetlistsResponse>
                } ?: Single.error(Unexpected(it))
            }
            .map { it.mapToArtistSetlistsResponse }
    }
}
