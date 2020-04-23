package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.entities.ArtistEntity
import io.reactivex.Single
import com.josuearevalodev.domain.repository.SetListFmRepository

class SearchArtistByNameImpl(private val setListFmRepository: SetListFmRepository) : SearchArtistByName {

    override fun invoke(artistName: String): Single<ArtistEntity> {
        return setListFmRepository.getArtist(artistName)
    }
}
