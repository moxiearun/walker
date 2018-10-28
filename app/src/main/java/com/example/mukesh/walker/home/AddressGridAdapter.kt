package com.example.mukesh.walker.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mukesh.walker.R
import com.example.mukesh.walker.datamodels.Location
import kotlinx.android.synthetic.main.grid_item_address.view.*

class AddressGridAdapter(private val addAddressListener: AddAdddressListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ADDRESS_VIEW_TYPE: Int = 1
    val ADD_ADDRESS_VIEW_TYPE: Int = 2

    var locationList: List<Location> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewResId: Int =
                if (viewType == ADDRESS_VIEW_TYPE) R.layout.grid_item_address else R.layout.grid_item_add_address
        val gridView: View = LayoutInflater.from(parent!!.context)
                .inflate(viewResId, parent, false)
        return if (viewType == ADDRESS_VIEW_TYPE) AddressListViewHolder(gridView) else AddAddressViewHolder(gridView, addAddressListener)
    }

    override fun getItemViewType(position: Int): Int =
            if (locationList?.size == position) ADD_ADDRESS_VIEW_TYPE else ADDRESS_VIEW_TYPE

    override fun getItemCount(): Int = locationList?.size + 1 ?: 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? AddressListViewHolder)?.bindView(locationList[position])
    }


    class AddressListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(location: Location) {
            view.addressNameView.text = location.name
            view.addressTextView.text = location.address
        }
    }

    class AddAddressViewHolder(val view: View, private val listener: AddAdddressListener) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener { listener.onAddAddressClick() }
        }

    }
}
