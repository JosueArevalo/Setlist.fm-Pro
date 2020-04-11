package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single

interface SearchArtistsByName {

    operator fun invoke(artistName: String): Single<SearchArtistsResponse>
}
