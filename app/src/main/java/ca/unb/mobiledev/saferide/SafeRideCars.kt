package ca.unb.mobiledev.saferide

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SafeRideCars : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_safe_ride_cars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val firstCar : ImageButton = findViewById(R.id.first_car)
        val secondCar: ImageButton = findViewById(R.id.second_car)
        val thirdCar: ImageButton = findViewById(R.id.third_car)

        firstCar.setOnClickListener{
            val intent = Intent(this@SafeRideCars, CarPhotos::class.java)
            intent.putExtra("KEY_VEHICLE", "firstCar")
            startActivity(intent)
        }

        secondCar.setOnClickListener{
            val intent = Intent(this@SafeRideCars, CarPhotos::class.java)
            intent.putExtra("KEY_VEHICLE", "secondCar")
            startActivity(intent)
        }

        thirdCar.setOnClickListener{
            val intent = Intent(this@SafeRideCars, CarPhotos::class.java)
            intent.putExtra("KEY_VEHICLE", "thirdCar")
            startActivity(intent)
        }
    }

    override fun onBackPressed(){
        AlertDialog.Builder(this)
            .setTitle("Confirm Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
                intent = Intent(this@SafeRideCars, LoginPage::class.java)
                startActivity(intent)
                finishAffinity()
            }
            .setNegativeButton("No", null)
            .show()
    }
}