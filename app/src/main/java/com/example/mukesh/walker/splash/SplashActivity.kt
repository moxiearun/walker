package com.example.mukesh.walker.splash

import android.content.Intent
import android.os.Bundle
import com.example.mukesh.walker.addresslist.AddressListActivity
import com.example.mukesh.walker.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToHomeScreen()
    }

    private fun navigateToHomeScreen() {
        startActivity(Intent(this, AddressListActivity::class.java))
    }
}