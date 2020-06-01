package com.josuearevalodev.usecases.setlists.getsetlistdetail

import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSetlistDetailImplTests {

    @Mock
    private lateinit var repository: SetListFmRepository

    private lateinit var getSetlistDetail: GetSetlistDetail

    @Before
    fun setUp() {
        getSetlistDetail = GetSetlistDetailImpl(repository)
    }

    @Test
    fun `invoke calls SetlistFm repository`() {
        // Given
        val setlistId = "fakeCustomId"

        // When
        val result = getSetlistDetail(setlistId)

        // Then
        verify(repository).getSetlistDetail(setlistId)
    }

    @Test
    fun `invoke returns same "SetlistEntity" entity than repository`() {
        // Given
        val fakeSetlistId = "fakeCustomId"
        val fakeSetlistEntity = SetlistEntity(id = fakeSetlistId)

        whenever(repository.getSetlistDetail(fakeSetlistId)).thenReturn(Single.just(fakeSetlistEntity))

        // When
        val result = getSetlistDetail(fakeSetlistId).test()

        // Then
        result.awaitTerminalEvent()
        result.assertComplete()
        result.assertValue {setlistEntity ->
            setlistEntity == fakeSetlistEntity
        }

        result.dispose()
    }
}
