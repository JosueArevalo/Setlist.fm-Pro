package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
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

    @Test
    fun `try to get artist - databaseDS getArtist() is called`() {

        // Given
        val artistName = "Artist Name"
        whenever(databaseDS.getArtist(artistName)).thenReturn(Single.just(ArtistEntity(name = "Artist Name")))

        // When
        val result = setListFmRepository.getArtist(artistName = artistName)

        // Then
        verify(databaseDS).getArtist(artistName = artistName)
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
    fun getArtistSetlists() {
        assertTrue(true);
    }

    @Test
    fun getSetlistDetail() {
        assertTrue(true);
    }

    @Test
    fun updateArtistWithSetlistsHeaderData() {
        assertTrue(true);
    }
}
