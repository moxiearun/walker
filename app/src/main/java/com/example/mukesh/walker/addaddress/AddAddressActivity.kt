package com.example.mukesh.walker.addaddress

import android.app.Activity
import android.os.Bundle
import com.example.mukesh.walker.R
import com.example.mukesh.walker.base.BaseActivity
import com.example.mukesh.walker.datamodels.Address
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.grid_item_address.*

class AddAddressActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        addAddressButton.setOnClickListener {
            val selectedAddress: Address = Address(3, addressNameView.text.toString().trim())
            intent.putExtra("address_key", selectedAddress)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}