package com.josuearevalodev.remote.setlistfm.datasource

import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.data.setlistfm.error.Unexpected
import com.josuearevalodev.domain.setlistfm.entities.*
import com.josuearevalodev.remote.setlistfm.service.SetlistFmService
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.setlistfm.mapper.*
import io.reactivex.Single
import retrofit2.HttpException

class RemoteSetlistFmDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : SetListFmRemoteDataSource {

    override fun getArtist(artistName: String): Single<ArtistEntity> {
        return httpClient.create(SetlistFmService::class.java, baseUrl)
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
    }

    override fun getArtistSetlists(artistId: String, page: Int): Single<List<SetlistEntity>> {
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
                remoteArtistSetlistResponse.setlist?.map { remoteSetlistEntity ->
                    remoteSetlistEntity.mapToSetlistEntity
                }
            }
    }
}
