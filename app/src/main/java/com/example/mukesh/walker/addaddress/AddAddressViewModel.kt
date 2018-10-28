package com.example.mukesh.walker.addaddress

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.mukesh.walker.base.BaseViewModel
import com.example.mukesh.walker.datamodels.Location
import com.google.android.gms.location.places.Place

class AddAddressViewModel(application: Application) : BaseViewModel(application) {

    var selectedLocation: MutableLiveData<Location> = MutableLiveData()

    fun setSelectedLocation(selctedPlace: Place, customName: String) {
        val location: Location = Location(
                if (customName?.length > 0) customName else selctedPlace.name.toString(),
                selctedPlace.address.toString(), selctedPlace.latLng)
        selectedLocation.value = location
    }

}