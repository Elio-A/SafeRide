package ca.unb.mobiledev.saferide

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class home_page_activity : Activity(){


    class MainActivity2 : AppCompatActivity() {
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
            val informationButton : Button = findViewById(R.id.information_button)

            stationButton.setOnClickListener{
                //Go to that activity
            }

            radiusButton.setOnClickListener{

            }

            workingHourButton.setOnClickListener{

            }

            informationButton.setOnClickListener{

            }
        }
    }
}
