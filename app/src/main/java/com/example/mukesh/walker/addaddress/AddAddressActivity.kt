package com.example.mukesh.walker.addaddress

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.mukesh.walker.R
import com.example.mukesh.walker.base.BaseActivity
import com.example.mukesh.walker.datamodels.Location
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class AddAddressActivity : BaseActivity() {

    companion object {
        const val KEY_ADDRESS: String = "address_key"
    }

    private val PLACE_AUTOCOMPLETE_REQUEST_CODE: Int = 100
    private var isAddressSelected: Boolean = false
    private lateinit var addAddressViewModel: AddAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        addAddressViewModel = ViewModelProviders.of(this).get(AddAddressViewModel::class.java)
        setSupportActionBar(toolbar)
        initViews()
        initObservers()
    }

    private fun initViews() {
        addAddressButton.setOnClickListener {
            if (isAddressSelected) {
                val returnIntent = Intent()
                returnIntent.putExtra(KEY_ADDRESS, addAddressViewModel.selectedLocation.value)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            } else {
                invokePlacesAutoCompleteIntents()
            }
        }
    }

    private fun initObservers() {
        val addressObserver = Observer<Location> { selectedAddress ->
            addressTextView.text = selectedAddress?.address
            isAddressSelected = selectedAddress != null
            if (selectedAddress != null) {
                addAddressButton.text = getString(R.string.save)
                addressTextView.visibility = View.VISIBLE
            }
        }
        addAddressViewModel.selectedLocation.observe(this, addressObserver)
    }

    private fun invokePlacesAutoCompleteIntents() {
        try {
            val placesIntent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(this)
            startActivityForResult(placesIntent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
        } catch (exception: GooglePlayServicesRepairableException) {
            showSnackBarMessage(addAddressContainer, exception.message.toString())
        } catch (exception: GooglePlayServicesNotAvailableException) {
            showSnackBarMessage(addAddressContainer, R.string.no_play_services_available)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            addAddressViewModel.setSelectedLocation(PlaceAutocomplete.getPlace(this, data),
                    addressNameEditText.text.toString().trim())
        }
    }
}