package com.josuearevalodev.usecases.setlists.getsetlistdetail

import com.josuearevalodev.domain.entities.SetlistEntity
import io.reactivex.Single

interface GetSetlistDetail {

    operator fun invoke(setlistId: String): Single<SetlistEntity>
}
