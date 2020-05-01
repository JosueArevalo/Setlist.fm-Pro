package com.josuearevalodev.usecases.setlists.getsetlistdetail

import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import io.reactivex.Single

class GetSetlistDetailImpl(private val setListFmRepository: SetListFmRepository) : GetSetlistDetail {

    override fun invoke(setlistId: String): Single<SetlistEntity> {
        return setListFmRepository.getSetlistDetail(setlistId = setlistId)
    }
}
