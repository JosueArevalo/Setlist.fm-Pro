package com.josuearevalodev.usecases.setlists.getsetlistdetail

import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import io.reactivex.Single

interface GetSetlistDetail {

    operator fun invoke(setlistId: String): Single<SetlistEntity>
}
