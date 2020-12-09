package com.example.metro2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LineAdapter(context: Context, data: List<LineModel>?) :
    RecyclerView.Adapter<LineAdapter.BranchViewHolder>() {

    private var mContext: Context = context
    private var items: List<LineModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var stationAdapter: StationAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        val view = inflater.inflate(R.layout.item_branch_station, parent, false)
        return BranchViewHolder(view)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val item = items?.get(position)

        holder.tvBranchStation.text = item?.line
        holder.rvBranch.setBackgroundColor(Color.parseColor(item?.color))

        stationAdapter = StationAdapter(mContext, item?.stations)
        holder.rvStation.adapter = stationAdapter

        holder.rvStation.layoutManager = LinearLayoutManager(mContext)
        holder.ivArrow.setOnClickListener { onItemClicked(item) }
        holder.tvBranchStation.setOnClickListener { onItemClicked(item) }

        if (item?.isExpanded!!) {
            holder.rvStation.visibility = View.VISIBLE
            holder.ivArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        } else {
            holder.rvStation.visibility = View.GONE
            holder.ivArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    private fun onItemClicked(newspaperModel: LineModel?) {
        newspaperModel?.isExpanded = !newspaperModel?.isExpanded!!
        notifyDataSetChanged()
    }

    class BranchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvBranch: RelativeLayout = itemView.findViewById(R.id.rvBranch)
        var tvBranchStation: TextView = itemView.findViewById(R.id.tvBranchStation)
        var rvStation: RecyclerView = itemView.findViewById(R.id.rvStation)
        var ivArrow: ImageView = itemView.findViewById(R.id.ivArrow)
    }
}