package com.example.mukesh.walker.home

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.mukesh.walker.base.BaseViewModel
import com.example.mukesh.walker.datamodels.Address

class AddressListViewModel(application: Application) : BaseViewModel(application) {

    var addressList: MutableLiveData<List<Address>> = MutableLiveData()
    private lateinit var addresses: ArrayList<Address>
    //    Todo(Arun A) : Remove once the actual implementation done
    fun loadMockData() {
        addresses = ArrayList()
        addresses.add(Address(1, "Home"))
        addresses.add(Address(2, "Laughing club"))
        addresses.add(Address(3, "Meeting point"))
        addressList.value = addresses
    }

    fun addNewAddress(newAddress: String) {
        addresses.add(Address(4, newAddress))
        addressList.value = addresses
    }

}