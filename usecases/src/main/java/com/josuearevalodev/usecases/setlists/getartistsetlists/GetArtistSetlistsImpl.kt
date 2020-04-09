package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.MockGenerator
import com.josuearevalodev.base.Result
import com.josuearevalodev.base.Success
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import io.reactivex.Single

class GetArtistSetlistsImpl() : GetArtistSetlists{

    override fun invoke(
        artistId: String,
        page: Int
    ): Single<Result<ArtistSetlistsResponse, GetArtistSetlistsError>> {
        return Single.just(Success(MockGenerator.artistSetlistsResponse))
    }

}
