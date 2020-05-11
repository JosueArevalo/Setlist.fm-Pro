package com.josuearevalodev.setlistfmpro.screens.artistsetlists.mapsection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.josuearevalodev.setlistfmpro.databinding.FragmentArtistSetlistsMapBinding
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ArtistSetlistsMapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentArtistSetlistsMapBinding? = null
    val binding get() = _binding!!

    private val sharedViewModel: ArtistSetlistsSharedViewModel by sharedViewModel<ArtistSetlistsSharedViewModelImpl>()
    private var googleMap: GoogleMap? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.mvMap.onCreate(savedInstanceState)
        binding.mvMap.onResume()
        binding.mvMap.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentArtistSetlistsMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addObservers() {
        sharedViewModel.setlists.observe(viewLifecycleOwner, Observer { setlists ->

            val setlistsMarkers = mutableListOf<CustomMarker>()
            setlists.forEach {
                setlistsMarkers.add(it.mapToSetlistMarker)
            }

            addMarkers(setlistsMarkers)
        })

        sharedViewModel.userLocation.observe(viewLifecycleOwner, Observer { userLocation ->
            googleMap?.addMarker(userLocation.mapToUserMarker)
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
