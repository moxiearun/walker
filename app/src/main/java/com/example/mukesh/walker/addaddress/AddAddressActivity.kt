package com.example.mukesh.walker.addaddress

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mukesh.walker.R
import com.example.mukesh.walker.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddressActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        addAddressButton.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("address_key", addressNameEditText.text.toString().trim())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}