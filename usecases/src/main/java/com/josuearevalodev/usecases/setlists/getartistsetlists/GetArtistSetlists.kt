package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import io.reactivex.Single

interface GetArtistSetlists {

    operator fun invoke(
        artistId: String,
        page: Int = 1,
        itemsPerPage: Int = 20
    ): Single<ArtistSetlistsResponseEntity>
}
