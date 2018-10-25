package com.example.mukesh.walker.home

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.mukesh.walker.base.BaseViewModel
import com.example.mukesh.walker.datamodels.Address

class AddressListViewModel(application: Application) : BaseViewModel(application) {

    var addressList: MutableLiveData<List<Address>> = MutableLiveData()

    //    Todo(Arun A) : Remove once the actual implementation done
    fun loadMockData() {
        var addresses: List<Address> = ArrayList()
        addresses = listOf(Address(1, "Home"), Address(2, "Laughing club"),
                Address(3, "Meeting point"))
        addressList.value = addresses
    }

}