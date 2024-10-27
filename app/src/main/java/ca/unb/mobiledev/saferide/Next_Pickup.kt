package ca.unb.mobiledev.saferide

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.unb.mobiledev.saferide.data.StationAdapter

class Next_Pickup : AppCompatActivity() {

    private val subTime = 2;
    private val stuTime = 11;
    private val iucTime = 5;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_pickup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textView = findViewById<TextView>(R.id.next)

        val stations = listOf(
            "STUDENT UNION BUILDING" to subTime,
            "SIR JAMES DUNN HALL (STU)" to stuTime,
            "I.U.C SCIENCE LIBRARY" to iucTime
        )

        textView.text = "${getNextPickupName(stations)}  (${getNextPickupTime(stations)} min)"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val stationAdapter = StationAdapter(stations)
        recyclerView.adapter = stationAdapter

        val backID = findViewById<Button>(R.id.backButton)
        backID.setOnClickListener {
            finish()
        }
    }

    private fun getNextPickupName(stations: List<Pair<String, Int>>): String{
        val (stationName, _) = stations.minByOrNull { it.second } ?: return "No stations available"
        return stationName
    }

    private fun getNextPickupTime(stations: List<Pair<String, Int>>): Int {
        return stations.minByOrNull { it.second }?.second ?: -1
    }
}
