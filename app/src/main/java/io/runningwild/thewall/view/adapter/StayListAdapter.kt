package io.runningwild.thewall.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.runningwild.thewall.R
import io.runningwild.thewall.persistence.Stay
import kotlinx.android.synthetic.main.item_stay.view.*

class StayListAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val items: List<Stay>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stay, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textEntryDate.text = items?.get(position)?.entryDate
        holder.textLeaveDate.text = items?.get(position)?.leaveDate
    }

    override fun getItemCount(): Int = items?.size ?: 0
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textEntryDate = view.text_stay_entry_date!!
    val textLeaveDate = view.text_stay_leave_date!!
}