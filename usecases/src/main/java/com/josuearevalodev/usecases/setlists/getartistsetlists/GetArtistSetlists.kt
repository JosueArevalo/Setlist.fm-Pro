package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import io.reactivex.Single

interface GetArtistSetlists {

    operator fun invoke(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
