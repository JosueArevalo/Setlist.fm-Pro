package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single
import com.josuearevalodev.base.Result
import com.josuearevalodev.base.Success

class SearchArtistsByNameImpl : SearchArtistsByName {

    override fun invoke(artistName: String): Single<Result<SearchArtistsResponse, SearchArtistsByNameError>> {
        val searchArtistsResponse = SearchArtistsResponse("MyType", 0 , 0, 0, listOf())
        return Single.just(Success(searchArtistsResponse))
    }
}
