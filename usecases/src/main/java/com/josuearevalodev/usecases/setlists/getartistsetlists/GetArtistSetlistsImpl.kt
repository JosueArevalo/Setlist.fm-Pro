package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import io.reactivex.Single

class GetArtistSetlistsImpl(private val setListFmRepository: SetListFmRepository) : GetArtistSetlists{

    override fun invoke(
        artistId: String,
        page: Int,
        itemsPerPage: Int
    ): Single<ArtistSetlistsResponseEntity> {
        return setListFmRepository.getArtistSetlists(artistId, page, itemsPerPage)
    }
}
