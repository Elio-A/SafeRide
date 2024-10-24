package ca.unb.mobiledev.saferide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Station(
    val name: String,
    var value: Int
)

class Next_Pickup : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var earliestAvailableTextView: TextView
    private lateinit var stationAdapter: StationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_pickup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        earliestAvailableTextView = findViewById(R.id.earliest)

        val stationList = mutableListOf(
            Station("Student Union Building", 5),
            Station("Sir James Dunn Hall (STU)", 7),
            Station("I.U.C Science Library", 2),
        )

        stationAdapter = StationAdapter(stationList)
        recyclerView.adapter = stationAdapter

        updateEarliestAvailableStation(stationList)
    }

    class StationAdapter(private var stations: MutableList<Station>) : RecyclerView.Adapter<StationAdapter.StationViewHolder>() {
        inner class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val stationNameTextView: TextView = itemView.findViewById(R.id.stationNameTextView)
            val stationValueTextView: TextView = itemView.findViewById(R.id.stationValueTextView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_station, parent, false)
            return StationViewHolder(view)
        }

        override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
            val station = stations[position]
            holder.stationNameTextView.text = station.name
            holder.stationValueTextView.text = station.value.toString()
        }

        override fun getItemCount(): Int {
            return stations.size
        }

        // Sort stations by value and update the RecyclerView
        fun updateStations(newStations: List<Station>) {
            stations = newStations.sortedBy { it.value }.toMutableList()
            notifyDataSetChanged()
        }
    }


    private fun updateEarliestAvailableStation(stations: List<Station>) {
        // Find the station with the least wait time
        val earliestStation = stations.minByOrNull { it.value }

        // Update the TextView with the station name
        earliestAvailableTextView.text = "Earliest available: ${earliestStation?.name ?: "N/A"}"
    }


}