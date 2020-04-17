package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single
import com.josuearevalodev.domain.repository.SetListFmRepository

class SearchArtistsByNameImpl(private val setListFmRepository: SetListFmRepository) : SearchArtistsByName {

    override fun invoke(artistName: String): Single<List<ArtistEntity>> {
        return setListFmRepository.getArtists(artistName)
    }
}
