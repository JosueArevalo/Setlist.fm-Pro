package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SetListFmRepositoryImplTests {

    @Mock
    private lateinit var databaseDS: SetListFmDatabaseDataSource

    @Mock
    private lateinit var remoteDS: SetListFmRemoteDataSource

    private lateinit var setListFmRepository: SetListFmRepository

    @Before
    fun setUp() {
        setListFmRepository = SetListFmRepositoryImpl(
            databaseDS = databaseDS,
            remoteDS = remoteDS
        )
    }

    //region getArtist

    @Test
    fun `try to get artist - databaseDS getArtist() is called`() {

        // Given
        val artistName = "Artist Name"
        whenever(databaseDS.getArtist(artistName)).thenReturn(Single.just(ArtistEntity(name = "Artist Name")))

        // When
        val test = setListFmRepository.getArtist(artistName = artistName).test()

        // Then
        verify(databaseDS).getArtist(artistName = artistName)
        test.dispose()
    }

    @Test
    fun `try to get artist - Got error from databaseDS - RemoteDS getArtist() called`() {
        // Given
        val artistName = "Artist Name"
        whenever(databaseDS.getArtist(any())).thenReturn(Single.error(Throwable()))

        // When
        val test = setListFmRepository.getArtist(artistName = artistName).test()
        test.awaitTerminalEvent()

        // Then
        test.assertError(Throwable::class.java)

        verify(databaseDS).getArtist(artistName)
        verify(remoteDS).getArtist(artistName)
        test.dispose()
    }

    @Test
    fun `try to get artist - Successful remote call - Entity is inserted in database`() {
        // Given
        val artistName = "Artist name"
        val artistEntity = ArtistEntity(name = artistName)

        whenever(databaseDS.getArtist(any())).thenReturn(Single.error(Throwable()))
        whenever(remoteDS.getArtist(any())).thenReturn(Single.just(artistEntity))

        // When
        val test = setListFmRepository.getArtist(artistName).test()
        test.awaitTerminalEvent()

        // Then
        verify(databaseDS).insertArtist(artistEntity)
        test.dispose()
    }

    //endregion

    //region getArtistSetlists
    @Test
    fun getArtistSetlists() {
        assertTrue(true);
    }

    //endregion

    //region getSetlistDetail
    @Test
    fun `try to get setlist detail - databaseDS's getSetlistDetail is called`() {
       // Given
        val setlistId = "0"
        whenever(databaseDS.getSetlistDetail(setlistId)).thenReturn(Single.just(SetlistEntity(id = setlistId)))

        // When
        val test = setListFmRepository.getSetlistDetail(setlistId = setlistId).test()

        // Then
        verify(databaseDS).getSetlistDetail(setlistId)
        test.dispose()
    }

    //endregion

    //region updateArtistWithSetlistsHeaderData
    @Test
    fun `try to update artist with setlist header data - databaseDS's updateArtistWithSetlistsHeaderData is called`() {
        // Given
        val idArtist = "0"
        val itemsPerPage = 20
        val totalSetlists = 100

        whenever(databaseDS.updateArtistWithSetlistsHeaderData(any(), any(), any())).thenReturn(
            Completable.complete())

        // When
        val test = setListFmRepository.updateArtistWithSetlistsHeaderData(
            idArtist = idArtist,
            itemsPerPage = itemsPerPage,
            totalSetlists = totalSetlists
        ).test()

        // Then
        verify(databaseDS).updateArtistWithSetlistsHeaderData(
            idArtist = idArtist,
            itemsPerPage = itemsPerPage,
            totalSetlists = totalSetlists
        )
        test.dispose()
    }
    //endregion
}
