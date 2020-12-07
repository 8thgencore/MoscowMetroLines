package com.example.metro2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(context: Context, stations: List<String>?) :
    RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    private var items: List<String>? = stations
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = inflater.inflate(R.layout.item_headlines, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val item = items?.get(position)
        holder.tvTitle.text = item
        // holder.tvDescription.text = item?.description
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        // var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }
}
