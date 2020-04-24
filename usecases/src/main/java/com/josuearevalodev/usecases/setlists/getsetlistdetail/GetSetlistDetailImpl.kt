package com.josuearevalodev.usecases.setlists.getsetlistdetail

import com.josuearevalodev.domain.entities.SetlistEntity
import io.reactivex.Single
import com.josuearevalodev.domain.repository.SetListFmRepository

class GetSetlistDetailImpl(private val setListFmRepository: SetListFmRepository) : GetSetlistDetail {

    override fun invoke(setlistId: String): Single<SetlistEntity> {
        return setListFmRepository.getSetlistDetail(setlistId = setlistId)
    }
}
