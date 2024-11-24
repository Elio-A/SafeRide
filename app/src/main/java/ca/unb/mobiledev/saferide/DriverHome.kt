package ca.unb.mobiledev.saferide

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DriverHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_driver_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val station = intent.getStringExtra("KEY_STATION")
        val frontImage = intent.getStringExtra("KEY_FRONTIMAGE")
        Log.i(TAG, "Extra: $frontImage")
        if(frontImage != null){
            Toast.makeText(this, "Front Image is taken", Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(this, "Station: $station", Toast.LENGTH_SHORT).show()
    }
}