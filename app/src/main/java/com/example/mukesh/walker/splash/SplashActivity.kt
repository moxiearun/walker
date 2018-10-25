package com.example.mukesh.walker.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.mukesh.walker.R
import com.example.mukesh.walker.home.AddressListActivity
import com.example.mukesh.walker.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToHomeScreen()
    }

    private fun navigateToHomeScreen() {
        Handler().postDelayed({
            startActivity(Intent(baseContext, AddressListActivity::class.java))
            finish()
        }, 2000)
    }
}