package ca.unb.mobiledev.saferide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Moving from main to home page using the button:
        val goToHomePageButton: Button = findViewById(R.id.next_activity)
        goToHomePageButton.setOnClickListener{
            val intent = Intent(this@MainActivity, HomePageActivity::class.java)
            startActivity(intent)
        }

        val goToSafeRideCars: Button = findViewById(R.id.saferide_cars_activity)
        goToSafeRideCars.setOnClickListener {
            val intent = Intent(this@MainActivity, SafeRideCars::class.java)
            startActivity(intent)
        }
    }
}//End MainActivity