package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import io.reactivex.Single

interface GetArtistSetlists {

    operator fun invoke(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
