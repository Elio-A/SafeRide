package ca.unb.mobiledev.saferide

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PickupStationsDriver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pickup_stations_driver)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton: Button = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        val nextButton: Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this@PickupStationsDriver, DriverHome::class.java)
            startActivity(intent)
        }

        val subButton: ImageButton = findViewById(R.id.imageButton)
        val headHallButton: ImageButton = findViewById(R.id.imageButton2)
        val stuButton: ImageButton = findViewById(R.id.STUImageButton)
        val currieButton: ImageButton = findViewById(R.id.CurrieButton)

        val buttons = listOf(subButton, headHallButton, stuButton, currieButton)

        for(button in buttons){
            button.setOnClickListener { view ->
                when (view.id) {
                    R.id.imageButton -> {
                        //save sub option
                        Log.i(TAG, "SUB option")
                    }

                    R.id.imageButton2 -> {
                        //save head hall button
                        Log.i(TAG, "Head hall Option")
                    }

                    R.id.STUImageButton -> {
                        //save stu option
                        Log.i(TAG, "STU option")
                    }

                    R.id.CurrieButton -> {
                        //save currie option
                        Log.i(TAG, "Currie Option")
                    }
                }
            }
            val intent = Intent(this, DriverHome::class.java)
            startActivity(intent)
        }
    }
}