package ca.unb.mobiledev.saferide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePageActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val stationButton : Button = findViewById(R.id.pickup_stations_button) //What's wrong with this?
        val radiusButton : Button = findViewById(R.id.radius_button)
        val workingHourButton : Button = findViewById(R.id.working_hours_button)
        val aboutUsButton : Button = findViewById(R.id.about_us_button)
        val driverViewButton: Button = findViewById(R.id.DriverButton)

        stationButton.setOnClickListener{
            val intent = Intent(this@HomePageActivity, stations::class.java)
            startActivity(intent)
        }

        radiusButton.setOnClickListener{
            val intent = Intent(this@HomePageActivity, Radius::class.java)
            startActivity(intent)
        }

        workingHourButton.setOnClickListener{
            val intent = Intent(this@HomePageActivity, WorkingHours::class.java)
            startActivity(intent)
        }

        aboutUsButton.setOnClickListener{
            val intent = Intent(this@HomePageActivity, Aboutus::class.java)
            startActivity(intent)
        }

        driverViewButton.setOnClickListener{
            val intent = Intent(this@HomePageActivity, SafeRideCars::class.java)
            startActivity(intent)
        }
    }
}//End HomePageActivity