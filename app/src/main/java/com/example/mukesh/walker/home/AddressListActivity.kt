package com.example.mukesh.walker.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.mukesh.walker.R
import com.example.mukesh.walker.base.BaseActivity
import com.example.mukesh.walker.datamodels.Address
import com.example.mukesh.walker.navigations.MapsActivity

import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.content_address_list.*

class AddressListActivity : BaseActivity() {

    private val addressGridAdapter: AddressGridAdapter = AddressGridAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        setSupportActionBar(toolbar)
        val addressListViewModel: AddressListViewModel =
                ViewModelProviders.of(this).get(AddressListViewModel::class.java)

        initViews()
        initObservers(addressListViewModel)
        fab.setOnClickListener {
            gotoMapScreen()
        }
        addressListViewModel.loadMockData()
    }

    private fun initViews() {
        addressListView.layoutManager = GridLayoutManager(this, 2)
        addressListView.adapter = addressGridAdapter
    }

    private fun initObservers(viewModel: AddressListViewModel) {
        val addressListObserver = Observer<List<Address>> { addressList ->
            // Update the UI, in this case, a TextView.
            addressGridAdapter.addressList = addressList!!.toList()
            addressGridAdapter.notifyDataSetChanged()
        }
        viewModel.addressList.observe(this, addressListObserver)
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
}
