package ca.unb.mobiledev.saferide

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val headlineTextView: TextView = itemView.findViewById(R.id.headline)
    val stationTextView: TextView = itemView.findViewById(R.id.station)
    val timeTextView: TextView = itemView.findViewById(R.id.time)

    fun bind(stationName: String, waitTime: Int) {
        headlineTextView.text = "Next pick-up time:"
        stationTextView.text = stationName
        timeTextView.text = "$waitTime min"
    }
}
