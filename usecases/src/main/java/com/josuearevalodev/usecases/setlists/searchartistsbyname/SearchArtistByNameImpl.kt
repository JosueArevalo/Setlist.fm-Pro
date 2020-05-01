package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import io.reactivex.Single

class SearchArtistByNameImpl(private val setListFmRepository: SetListFmRepository) : SearchArtistByName {

    override fun invoke(artistName: String): Single<ArtistEntity> {
        return setListFmRepository.getArtist(artistName)
    }
}
