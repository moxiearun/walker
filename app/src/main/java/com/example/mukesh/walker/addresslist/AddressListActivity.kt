package com.example.mukesh.walker.addresslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.mukesh.walker.R
import com.example.mukesh.walker.base.BaseActivity

import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            showSnackBarMessage(view, R.string.app_name)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_address_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
