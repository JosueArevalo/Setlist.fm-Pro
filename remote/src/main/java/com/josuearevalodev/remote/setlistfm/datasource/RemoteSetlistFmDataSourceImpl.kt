package com.josuearevalodev.remote.setlistfm.datasource

import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.data.setlistfm.error.NoInternetConnection
import com.josuearevalodev.data.setlistfm.error.Unexpected
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.setlistfm.mapper.mapToArtistEntity
import com.josuearevalodev.remote.setlistfm.mapper.mapToArtistSetlistsResponseEntity
import com.josuearevalodev.remote.setlistfm.mapper.mapToRemoteError
import com.josuearevalodev.remote.setlistfm.service.SetlistFmService
import com.josuearevalodev.remote.utils.NetworkInfoProvider
import io.reactivex.Single
import retrofit2.HttpException

class RemoteSetlistFmDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val networkInfoProvider: NetworkInfoProvider
) : SetListFmRemoteDataSource {

    override fun getArtist(artistName: String): Single<ArtistEntity> {
        return if (networkInfoProvider.hasInternetConnection()) {
            httpClient.create(SetlistFmService::class.java, baseUrl)
                .getArtists(artistName)
                .onErrorResumeNext {
                    if (it is HttpException) {
                        Single.error(it.mapToRemoteError)
                    } else {
                        Single.error(Unexpected(it))
                    }
                }
                .map { it.artist?.map { it.mapToArtistEntity } ?: listOf() }
                .map { it.firstOrNull() }
        } else {
            Single.error(NoInternetConnection())
        }
    }

    override fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponseEntity> {
        return httpClient.create(SetlistFmService::class.java, baseUrl)
            .getArtistSetlists(artistId, page)
            .onErrorResumeNext {
                if (it is HttpException) {
                    Single.error(it.mapToRemoteError)
                } else {
                    Single.error(Unexpected(it))
                }
            }
            .map { remoteArtistSetlistResponse ->
                remoteArtistSetlistResponse.mapToArtistSetlistsResponseEntity
            }
    }
}
