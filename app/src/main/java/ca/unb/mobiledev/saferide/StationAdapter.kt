package ca.unb.mobiledev.saferide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.unb.mobiledev.saferide.R
import ca.unb.mobiledev.saferide.StationViewHolder

class StationAdapter(private val stations: List<Map<String, Any>>) : RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stations[position]

        // Extract values from the map
        val image = station["image"] as Int
        val stationName = station["stationName"] as String
        val headline = station["headline"] as String
        val status = station["status"] as String
        val seats = station["seats"] as Int
        val waitTime = station["waitTime"] as Int
        val license = station["license"] as String

        holder.bind(image, headline, stationName, status, seats, waitTime, license)
    }

    override fun getItemCount(): Int {
        return stations.size
    }
}
