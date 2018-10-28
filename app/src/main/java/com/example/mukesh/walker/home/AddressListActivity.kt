package com.example.mukesh.walker.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.mukesh.walker.R
import com.example.mukesh.walker.addaddress.AddAddressActivity
import com.example.mukesh.walker.base.BaseActivity
import com.example.mukesh.walker.datamodels.Location
import com.example.mukesh.walker.navigations.MapsActivity

import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.content_address_list.*

class AddressListActivity : BaseActivity(), AddAdddressListener {

    private val ADD_ADDRESS_REQEUST = 100
    private val addressGridAdapter: AddressGridAdapter = AddressGridAdapter(this)
    lateinit var addressListViewModel: AddressListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        setSupportActionBar(toolbar)

        addressListViewModel = ViewModelProviders.of(this).get(AddressListViewModel::class.java)

        initViews()
        initObservers()
        fab.setOnClickListener {
            gotoMapScreen()
        }
    }

    private fun initViews() {
        addressListView.layoutManager = GridLayoutManager(this, 2)
        addressListView.adapter = addressGridAdapter
    }

    private fun initObservers() {
        val addressListObserver = Observer<List<Location>> { addressList ->
            // Update the UI, in this case, a TextView.
            addressGridAdapter.locationList = addressList!!.toList()
            addressGridAdapter.notifyDataSetChanged()
        }
        addressListViewModel.locationList.observe(this, addressListObserver)
    }

    private fun gotoMapScreen() {
        val mapIntent = Intent(this, MapsActivity::class.java)
        startActivity(mapIntent)
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAddAddressClick() {
        val addAddressIntent = Intent(this, AddAddressActivity::class.java)
        startActivityForResult(addAddressIntent, ADD_ADDRESS_REQEUST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == ADD_ADDRESS_REQEUST) {
            val selectedLocation: Location = data!!.getParcelableExtra(AddAddressActivity.KEY_ADDRESS)
            addressListViewModel.addNewAddress(selectedLocation)
        }
    }
}
