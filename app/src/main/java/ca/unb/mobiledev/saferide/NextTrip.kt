package ca.unb.mobiledev.saferide

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import ca.unb.mobiledev.saferide.DatabaseHelpers.LocationHelper

class NextTrip : AppCompatActivity() {

    private lateinit var dbLocationHelper: LocationHelper
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var locationsTextView: TextView
    private lateinit var backButton: Button
    private lateinit var completeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_trip)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbLocationHelper = LocationHelper(this)
        val station = intent.getStringExtra("KEY_STATION")?: "DEFAULT"
        val vehicle = intent.getStringExtra("KEY_VEHICLE")?: "DEFAULT"


        val seat = if (vehicle == "firstCar" || vehicle == "secondCar") {
            4
        } else {
            6
        }
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        locationsTextView = findViewById(R.id.locationsTextView)

        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        //dbLocationHelper.getAllRows()

        locationViewModel.locationLiveData.observe(this) { locations ->
            try {
                if (locations.isNotEmpty()) {
                    val locationsText = locations.joinToString("\n") { location ->
                        "User: ${location.user}, Destination: ${location.location}"
                    }
                    locationsTextView.text = locationsText
                } else {
                    Toast.makeText(this, "No upcoming trips for this station", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }

        locationViewModel.getLocationForStation(station, seat)

        completeButton = findViewById(R.id.comepleteButton)
        completeButton.setOnClickListener {
            dbLocationHelper.completeTrip()
            finish()
        }
    }
}