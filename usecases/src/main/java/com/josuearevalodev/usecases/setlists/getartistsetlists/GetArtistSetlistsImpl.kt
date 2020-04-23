package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SetlistEntity
import com.josuearevalodev.domain.repository.SetListFmRepository
import io.reactivex.Single

class GetArtistSetlistsImpl(private val setListFmRepository: SetListFmRepository) : GetArtistSetlists{

    override fun invoke(
        artistId: String,
        page: Int
    ): Single<List<SetlistEntity>> {
        return setListFmRepository.getArtistSetlists(artistId, page)
    }
}
