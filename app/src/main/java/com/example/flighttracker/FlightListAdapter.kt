package com.example.flighttracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp2022.FlightInfoCell

class FlightListAdapter(val flightList: List<FlightModel>) : RecyclerView.Adapter<FlightListAdapter.FlightListCelleViewHolder>() {

    class FlightListCelleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListCelleViewHolder {
        val cell = FlightInfoCell(parent.context)
        cell.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return FlightListCelleViewHolder(cell)
    }

    override fun onBindViewHolder(holder: FlightListCelleViewHolder, position: Int) {
        val flight = flightList[position]
        val cell = holder.itemView as FlightInfoCell
        cell.bindData(flight)
    }

    override fun getItemCount(): Int {
       return flightList.size
    }

}