package com.josuearevalodev.usecases.location.getcurrentlocation

import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.location.repository.LocationRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCurrentLocationImplTests {

    @Mock
    lateinit var locationRepository: LocationRepository

    lateinit var getCurrentLocation: GetCurrentLocation

    @Before
    fun setUp() {
        getCurrentLocation = GetCurrentLocationImpl(
            repository = locationRepository
        )
    }

    @Test
    fun `invoke calls location repository`() {

        // Given (None)

        // When
        val result = getCurrentLocation()

        // Then
        verify(locationRepository).getCurrentLocation()
    }

    @Test
    fun `invoke returns same Location entity than repository`() {
        // Given (None)
        val fakeMaybeLocation = Maybe.just(LocationEntity(
            longitude = 42.0f,
            latitude = 2.0f
        ))

        whenever(locationRepository.getCurrentLocation()).thenReturn(fakeMaybeLocation)

        // When
        val result = getCurrentLocation()

        // Then
        Assert.assertEquals(fakeMaybeLocation, result)
    }
}
