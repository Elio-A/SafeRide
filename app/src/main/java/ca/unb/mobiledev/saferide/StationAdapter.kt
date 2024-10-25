package ca.unb.mobiledev.saferide.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.unb.mobiledev.saferide.R
import ca.unb.mobiledev.saferide.StationViewHolder

class StationAdapter(private val stations: List<Pair<String, Int>>) : RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val (stationName, waitTime) = stations[position]
        holder.bind(stationName, waitTime)
    }

    override fun getItemCount(): Int {
        return stations.size
    }
}
