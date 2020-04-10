package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.base.Failure
import com.josuearevalodev.base.Result
import com.josuearevalodev.base.Success
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.repository.SetListFmRepository
import io.reactivex.Single

class GetArtistSetlistsImpl(private val setListFmRepository: SetListFmRepository) : GetArtistSetlists{

    override fun invoke(
        artistId: String,
        page: Int
    ): Single<Result<ArtistSetlistsResponse, GetArtistSetlistsError>> {
        return setListFmRepository.getArtistSetlists(artistId, page)
            .map { it.mapToGetArtistSetlistsError }
    }

    val Result<ArtistSetlistsResponse, Throwable>.mapToGetArtistSetlistsError: Result<ArtistSetlistsResponse, GetArtistSetlistsError>
    get() {
        return when (this) {
            is Success -> { Success(this.value)}
            is Failure -> { Failure(GetArtistSetlistsError(this.error))}
        }
    }

}
