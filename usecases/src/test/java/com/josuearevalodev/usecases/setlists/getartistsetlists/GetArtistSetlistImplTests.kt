package com.josuearevalodev.usecases.setlists.getartistsetlists

import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetArtistSetlistImplTests {

    @Mock
    lateinit var setListFmRepository: SetListFmRepository

    lateinit var getArtistSetlists: GetArtistSetlists

    @Before
    fun setUp() {
        getArtistSetlists = GetArtistSetlistsImpl(
            setListFmRepository = setListFmRepository
        )
    }

    @Test
    fun `invoke calls SetlistFm repository`() {
        // Given
        val artistId = "Artist Id"
        val page = 1
        val itemsPerPage = 20

        // When
        val result = getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        )
        // Then
        verify(setListFmRepository).getArtistSetlists(artistId, page, itemsPerPage)
    }

    @Test
    fun `invoke returns same SetlistResponse entity than repository`() {
        // Given
        val artistId = "Artist Id"
        val page = 1
        val itemsPerPage = 20

        val fakeArtistSetlistResponse = ArtistSetlistsResponseEntity(
            type = "My custom type",
            itemsPerPage = 20,
            page = 1,
            total = 100,
            setlist = listOf()
        )

        whenever(setListFmRepository.getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage
        )).thenReturn(
            Single.just(fakeArtistSetlistResponse)
        )

        // When
        val testObserver = getArtistSetlists(
            artistId = artistId,
            page = page,
            itemsPerPage = itemsPerPage).test()

        // Then
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue { artistSetlistResponseEntity ->
            when (artistSetlistResponseEntity) {
                fakeArtistSetlistResponse -> true
                else -> false
            }
        }
        testObserver.dispose()
    }
}
