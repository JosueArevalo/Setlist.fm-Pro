package com.josuearevalodev.data.location.repository

import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.location.repository.LocationRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryImplTest {

    @Mock
    private lateinit var locationDataSource: LocationDataSource

    private lateinit var locationRepository: LocationRepository

    @Before
    fun setUp() {
        locationRepository = LocationRepositoryImpl(locationDataSource)
    }

    @Test
    fun `GetCurrentLocation() calls LocationDS's getCurrentLocation() `() {

        // Given
        whenever(locationDataSource.getCurrentLocation()).thenReturn(Maybe.just(LocationEntity()))

        // When
        val test = locationRepository.getCurrentLocation().test()

        // Then
        verify(locationDataSource).getCurrentLocation()
        test.dispose()
    }

    @Test
    fun `GetCurrentLocation() returns same values that Data source`() {

        // Given
        val fakeLocation = LocationEntity(
            longitude = 42.0f,
            latitude = 2.0f
        )
        whenever(locationDataSource.getCurrentLocation()).thenReturn(Maybe.just(fakeLocation))

        // When
        val test = locationRepository.getCurrentLocation().test()
        test.awaitTerminalEvent()
        test.assertComplete()

        // Then
        test.assertValue {
            it.latitude == fakeLocation.latitude && it.longitude == fakeLocation.longitude
        }

        test.dispose()
    }
}
