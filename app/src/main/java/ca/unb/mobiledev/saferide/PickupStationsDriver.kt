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
        val vehicle = intent.getStringExtra("KEY_VEHICLE")?: "UNKNOWN VEHICLE"
//        val frontImage: Bitmap? = intent.getParcelableExtra("KEY_FRONT_IMAGE")
//        val rightImage: Bitmap? = intent.getParcelableExtra("KEY_RIGHT_IMAGE")
//        val backImage: Bitmap? = intent.getParcelableExtra("KEY_BACK_IMAGE")
//        val leftImage: Bitmap? = intent.getParcelableExtra("KEY_LEFT_IMAGE")

        val backButton: Button = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        val subButton: ImageButton = findViewById(R.id.imageButton)
        val stuButton: ImageButton = findViewById(R.id.STUImageButton)
        val currieButton: ImageButton = findViewById(R.id.CurrieButton)

        val buttons = listOf(subButton, stuButton, currieButton)

        for(button in buttons){
            val intent = Intent(this, DriverHomePage::class.java)

            intent.putExtra("KEY_VEHICLE", vehicle)
//            intent.putExtra("KEY_FRONT_IMAGE", frontImage)
//            intent.putExtra("KEY_RIGHT_IMAGE", rightImage)
//            intent.putExtra("KEY_BACK_IMAGE", backImage)
//            intent.putExtra("KEY_LEFT_IMAGE", leftImage)

            button.setOnClickListener { view ->
                when (view.id) {
                    R.id.imageButton -> {
                        //save sub option
                        intent.putExtra("KEY_STATION", "SUB")
                        Log.i(TAG, "SUB option")
                    }

                    R.id.imageButton2 -> {
                        //save head hall button
                        intent.putExtra("KEY_STATION", "head hall")
                        Log.i(TAG, "IUC Option")
                    }

                    R.id.STUImageButton -> {
                        //save stu option
                        intent.putExtra("KEY_STATION", "Sir James Dunn Hall")
                        Log.i(TAG, "STU option")
                    }

                    R.id.CurrieButton -> {
                        //save currie option
                        intent.putExtra("KEY_STATION", "Richard Currie Center")
                        Log.i(TAG, "Currie Option")
                    }
                }
                startActivity(intent)
            }
        }
    }
}