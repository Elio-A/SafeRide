package ca.unb.mobiledev.saferide

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NextLocations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_locations)

        val extras = intent.extras

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var seat = 0
        var station = 0

        if (extras != null) {
            seat = extras.getInt("SEAT")
            station = extras.getInt("STATION")
        }

        //val nextStops: LiveData<List<Dropofflocation>> = nextStops.getNextPassengers(seat, station)

    }
}