package ca.unb.mobiledev.saferide

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ManualInput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manual_input)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton: Button = findViewById(R.id.backButton)
        val submitButton: Button = findViewById(R.id.Submit)

        backButton.setOnClickListener {
            finish()
        }

        submitButton.setOnClickListener {
            val addressInput = findViewById<EditText>(R.id.AddressTextViewInput).text.toString()
            Log.i(TAG, "Input: $addressInput")
        }

    }
}