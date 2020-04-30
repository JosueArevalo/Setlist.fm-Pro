package com.josuearevalodev.setlistfmpro.screens.artistsetlists.mapsection

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import kotlinx.android.synthetic.main.fragment_artist_setlists_map.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ArtistSetlistsMapFragment : Fragment(R.layout.fragment_artist_setlists_map), OnMapReadyCallback {

    val sharedViewModel: ArtistSetlistsSharedViewModel by sharedViewModel<ArtistSetlistsSharedViewModelImpl>()

    private var googleMap: GoogleMap? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mvMap.onCreate(savedInstanceState)
        mvMap.onResume()
        mvMap.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    private fun addObservers() {
        sharedViewModel.setlists.observe(viewLifecycleOwner, Observer {setlists ->
            Log.d("TEST", "TEST: Received ${setlists.size}")

            val setlistsMarkers = mutableListOf<CustomMarker>()
            setlists.forEach {
                setlistsMarkers.add(it.mapToSetlistMarker)
            }

            addMarkers(setlistsMarkers)
        })
    }


    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
        }
    }

    fun addMarkers(markers: List<CustomMarker>) {
        markers.forEach { marker ->
            googleMap?.addMarker(marker)
        }

        markers.lastOrNull()?.let { marker ->
            googleMap?.let {
                val latLong = LatLng(
                    marker.location.latitude.toDouble(),
                    marker.location.longitude.toDouble()
                    )
                it.moveCamera(CameraUpdateFactory.newLatLng(latLong))
            }
        }
    }

    fun GoogleMap.addMarker(marker: CustomMarker) {
        val position = LatLng(
            marker.location.latitude.toDouble(),
            marker.location.longitude.toDouble()
        )

        addMarker(
            MarkerOptions()
                .position(position)
                .title(marker.title)
                .icon(marker.color.mapToBitmapDescriptor())
        )
    }
}
