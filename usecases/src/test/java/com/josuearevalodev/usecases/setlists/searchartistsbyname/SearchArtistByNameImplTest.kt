package com.josuearevalodev.usecases.setlists.searchartistsbyname

import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchArtistByNameImplTests {

    @Mock
    private lateinit var setListFmRepository: SetListFmRepository

    private lateinit var searchArtistByName: SearchArtistByName

    @Before
    fun setUp() {
        searchArtistByName = SearchArtistByNameImpl(setListFmRepository = setListFmRepository)
    }

    @Test
    fun `invoke calls repository`() {
        // Given
        val artistName = "My awesome band"

        // When
        val result = searchArtistByName(artistName = artistName)

        // Then
        verify(setListFmRepository).getArtist(artistName = artistName)
    }
}
