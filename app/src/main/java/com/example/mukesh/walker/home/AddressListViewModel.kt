package com.example.mukesh.walker.home

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.mukesh.walker.base.BaseViewModel
import com.example.mukesh.walker.datamodels.Location

class AddressListViewModel(application: Application) : BaseViewModel(application) {

    var locationList: MutableLiveData<List<Location>> = MutableLiveData()
    private var locations: ArrayList<Location> = ArrayList()

    fun addNewAddress(newLocation: Location) {
        locations.add(newLocation)
        locationList.value = locations
    }

    fun hasMinimumAddressCount(): Boolean {
        return locations.size > 1
    }

    fun getLocationList(): ArrayList<Location> {
        return locations
    }

}