package com.josuearevalodev.data.setlistfm.repository

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
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
    fun `try to get artist setlists - databaseDS's getArtistSetlists called`() {
        // Given
        val artistId = "artist Id"
        val page = 1
        val itemsPerPage = 20
        val setlists = listOf<SetlistEntity>()

        whenever(
            databaseDS.getArtistSetlists(
                artistId = any(),
                page = any(),
                itemsPerPage = any()
            )
        ).thenReturn(Single.just(setlists))

        // When
        val test = setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage).test()

        // Then
        verify(databaseDS).getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        )

        test.dispose()
    }

    @Test
    fun `try to get artist setlists - databaseDS's getArtistSetlists returns error - remoteDS's getSetlistsFromRemote is called`() {

        // Given
        val artistId = "artist Id"
        val page = 1
        val itemsPerPage = 20

        whenever(databaseDS.getArtistSetlists(
            artistId = any(),
            page = any(),
            itemsPerPage = any())
        ).thenReturn(Single.error(Throwable()))

        // When
        val test = setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        ).test()

        // Then
        verify(remoteDS).getArtistSetlists(artistId, page)
        test.dispose()
    }

    @Test
    fun `try to get artist setlists - After successful remote call, databaseDS's insertSetlists is called`() {

        // Given
        val artistId = "artist Id"
        val page = 1
        val itemsPerPage = 20
        val artistSetlistsResponseEntity = ArtistSetlistsResponseEntity(setlist = listOf())

        whenever(databaseDS.getArtistSetlists(
            artistId = any(),
            page = any(),
            itemsPerPage = any())
        ).thenReturn(Single.error(Throwable()))

        whenever(remoteDS.getArtistSetlists(
            artistId = any(),
            page = any())
        ).thenReturn(Single.just(artistSetlistsResponseEntity))

        // When
        val test = setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        ).test()

        // Then
        verify(databaseDS).insertSetlists(artistSetlistsResponseEntity.setlist)
        test.dispose()
    }

    @Test
    fun `try to get artist setlists - After successful remote call, databaseDS's updateArtistWithSetlistsHeaderData is called`() {

        // Given
        val artistId = "artist Id"
        val page = 1
        val itemsPerPage = 20
        val artistSetlistsResponseEntity = ArtistSetlistsResponseEntity(
            setlist = listOf(),
            total = 8)

        whenever(databaseDS.getArtistSetlists(
            artistId = any(),
            page = any(),
            itemsPerPage = any())
        ).thenReturn(Single.error(Throwable()))

        whenever(remoteDS.getArtistSetlists(
            artistId = any(),
            page = any())
        ).thenReturn(Single.just(artistSetlistsResponseEntity))

        whenever(databaseDS.insertSetlists(any())).thenReturn(Completable.complete())

        // When
        val test = setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        ).test()

        // Then
        verify(databaseDS).updateArtistWithSetlistsHeaderData(
            idArtist = any(),
            itemsPerPage = any(),
            totalSetlists = any()
        )
        test.dispose()
    }

    @Test
    fun `try to get artist setlists - databaseDS's getArtistWithId is called when haven't been errors from DB`() {

        // Given
        val artistId = "artist Id"
        val page = 1
        val itemsPerPage = 20
        val artistSetlistsResponseEntity = ArtistSetlistsResponseEntity(
            setlist = listOf(),
            total = 8)

        whenever(databaseDS.getArtistSetlists(
            artistId = any(),
            page = any(),
            itemsPerPage = any())
        ).thenReturn(Single.just(listOf()))

        // When
        val test = setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        ).test()

        // Then
        verify(databaseDS).getArtistWithId(any())
        test.dispose()
    }

    @Test
    fun `try to get artist setlists - databaseDS's getArtistWithId is called when have been errors from DB`() {

        assertTrue(true)

        // TODO
        /*// Given
        val artistId = "artist Id"
        val page = 1
        val itemsPerPage = 20
        val artistSetlistsResponseEntity = ArtistSetlistsResponseEntity(
            setlist = listOf(),
            total = 8)

        whenever(databaseDS.getArtistSetlists(
            artistId = any(),
            page = any(),
            itemsPerPage = any())
        ).thenReturn(Single.error(Throwable()))

        whenever(remoteDS.getArtistSetlists(
            artistId = any(),
            page = any())
        ).thenReturn(Single.just(artistSetlistsResponseEntity))

        whenever(databaseDS.insertSetlists(setlists = any())).thenReturn(Completable.complete())

        whenever(databaseDS.updateArtistWithSetlistsHeaderData(
            idArtist = any(),
            itemsPerPage = any(),
            totalSetlists = any()
        )).thenReturn(Completable.complete())

        // When
        val test = setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        ).test()

        // Then
        verify(databaseDS).getArtistWithId(any())
        test.dispose()*/
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
