package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UpdateArtistWithSetlistsHeaderData {

    operator fun invoke(idArtist: String, itemsPerPage: Int, totalSetlists: Int): Completable
}
