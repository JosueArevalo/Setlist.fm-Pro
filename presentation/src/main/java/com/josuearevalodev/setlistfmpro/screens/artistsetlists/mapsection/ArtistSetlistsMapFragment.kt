package com.josuearevalodev.setlistfmpro.screens.artistsetlists.mapsection

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import kotlinx.android.synthetic.main.fragment_artist_setlists_map.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ArtistSetlistsMapFragment : Fragment(R.layout.fragment_artist_setlists_map), OnMapReadyCallback {

    val sharedViewModel: ArtistSetlistsSharedViewModel by sharedViewModel<ArtistSetlistsSharedViewModelImpl>()

    private lateinit var googleMap: GoogleMap

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
        })
    }


    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            val dummyPosition = LatLng(41.5421104, 2.4445)
            googleMap
                .addMarker(
                    MarkerOptions()
                        .position(dummyPosition)
                        .title("Dummy!")
                        .icon(getMarkerIcon("#FF00FF"))
                )

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(dummyPosition))

        }
    }

    fun getMarkerIcon(color: String?): BitmapDescriptor? {
        val hsv = FloatArray(3)
        Color.colorToHSV(Color.parseColor(color), hsv)
        return BitmapDescriptorFactory.defaultMarker(hsv[0])
    }
}
