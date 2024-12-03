package ca.unb.mobiledev.saferide

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Next_Pickup : AppCompatActivity() {

    private val subTime = 0
    private val stuTime = 0
    private val iucTime = 1
    private val subieSeat = 4
    private val vanSeat = 4

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
            mapOf(
                "image" to R.drawable.dodge_van_resized,
                "stationName" to "STUDENT UNION BUILDING",
                "headline" to "Status:",
                "status" to getStatus(subTime),
                "seats" to vanSeat,
                "waitTime" to subTime,
                "license" to "KAV699"
            ),
            mapOf(
                "image" to R.drawable.subie_suv_resized,
                "stationName" to "SIR JAMES DUNN HALL",
                "headline" to "Status:",
                "status" to getStatus(stuTime),
                "seats" to subieSeat,
                "waitTime" to stuTime,
                "license" to "KCT397"
            ),
            mapOf(
                "image" to R.drawable.subie_suv_resized,
                "stationName" to "I.U.C SCIENCE LIBRARY",
                "headline" to "Status:",
                "status" to getStatus(iucTime),
                "seats" to subieSeat,
                "waitTime" to iucTime,
                "license" to "JYT043"
            )
        )
        val nextPickupTime = getNextPickupTime(stations)
        if (nextPickupTime == 0) {
            val stationName = getNextPickupName(stations)
            val availableText = " (Available)"
            val spannableString = SpannableString("$stationName$availableText")

            spannableString.setSpan(
                ForegroundColorSpan(Color.GREEN),
                stationName.length,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            textView.text = spannableString
        }
        else {
            textView.text = "${getNextPickupName(stations)}  (${getNextPickupTime(stations)} min)"
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val stationAdapter = StationAdapter(stations)
        recyclerView.adapter = stationAdapter

        val backID = findViewById<Button>(R.id.backButton)
        backID.setOnClickListener {
            finish()
        }
    }

    private fun getNextPickupName(stations: List<Map<String, Any>>): String {
        val minStation = stations.minByOrNull { it["waitTime"] as Int }
        return minStation?.get("stationName") as? String ?: "No stations available"
    }

    private fun getNextPickupTime(stations: List<Map<String, Any>>): Int {
        return stations.minByOrNull { it["waitTime"] as Int }?.get("waitTime") as? Int ?: -1
    }

    private fun getStatus(waitTime: Int): String {
        return if (waitTime == 0) "Available" else "On Delivery"
    }
}
