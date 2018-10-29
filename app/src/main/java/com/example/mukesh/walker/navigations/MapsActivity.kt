package com.example.mukesh.walker.navigations

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mukesh.walker.R
import com.example.mukesh.walker.datamodels.Location

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.layout_toolbar.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val KEY_LOCATIONS: String = "key_locations"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var locationList: ArrayList<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        setSupportActionBar(toolbar)
        initViews()
        parseLocations()
    }

    private fun parseLocations() {
        locationList = ArrayList()
        locationList = intent.getParcelableArrayListExtra(KEY_LOCATIONS)
    }

    private fun initViews() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (location in locationList) {
            mMap.addMarker(MarkerOptions().position(location.latLng))
        }

        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(locationList.firstOrNull()?.latLng, 12.0f))
        drawPolylines()
    }

    private fun drawPolylines() {
        for (i in 0 until locationList.size - 1) {
            mMap.addPolyline(PolylineOptions()
                    .add(locationList[i]?.latLng, locationList[i + 1]?.latLng)
                    .width(2.0f).color(Color.BLACK))
        }
    }
}
