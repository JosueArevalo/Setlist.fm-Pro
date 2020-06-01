package com.josuearevalodev.remote.setlistfm.service

import com.josuearevalodev.remote.setlistfm.entities.RemoteArtistSetlistsResponse
import com.josuearevalodev.remote.setlistfm.entities.RemoteSearchArtistsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SetlistFmService {

    @GET("search/artists?sort=relevance")
    fun getArtists(@Query("artistName") artistName: String): Single<RemoteSearchArtistsResponse>

    @GET("artist/{artistId}/setlists")
    fun getArtistSetlists(@Path("artistId") artistId: String, @Query("p") page: Int): Single<RemoteArtistSetlistsResponse>
}
