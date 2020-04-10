package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.base.Failure
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single
import com.josuearevalodev.base.Result
import com.josuearevalodev.base.Success
import com.josuearevalodev.domain.repository.SetListFmRepository

class SearchArtistsByNameImpl(private val setListFmRepository: SetListFmRepository) : SearchArtistsByName {

    override fun invoke(artistName: String): Single<Result<SearchArtistsResponse, SearchArtistsByNameError>> {
        return setListFmRepository.getArtists(artistName)
            .map { it.mapToSearchArtistsByNameError }

    }

    val Result<SearchArtistsResponse, Throwable>.mapToSearchArtistsByNameError: Result<SearchArtistsResponse, SearchArtistsByNameError>
    get() {
        return when (this) {
            is Success -> { Success(this.value)}
            is Failure -> { Failure(SearchArtistsByNameError(this.error))}
        }
    }

}
