package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import io.reactivex.Single

interface SearchArtistByName {

    operator fun invoke(artistName: String): Single<ArtistEntity>
}
