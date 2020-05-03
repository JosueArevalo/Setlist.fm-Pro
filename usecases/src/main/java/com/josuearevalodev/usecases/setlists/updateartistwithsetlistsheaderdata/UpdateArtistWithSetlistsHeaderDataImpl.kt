package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import io.reactivex.Completable
import io.reactivex.Single

class UpdateArtistWithSetlistsHeaderDataImpl(private val setListFmRepository: SetListFmRepository) : UpdateArtistWithSetlistsHeaderData {

    override fun invoke(idArtist: String, itemsPerPage: Int, totalSetlists: Int): Completable {
        return setListFmRepository.updateArtistWithSetlistsHeaderData(
            idArtist = idArtist,
            itemsPerPage = itemsPerPage,
            totalSetlists = totalSetlists
        )
    }
}
