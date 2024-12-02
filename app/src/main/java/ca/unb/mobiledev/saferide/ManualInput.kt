package ca.unb.mobiledev.saferide

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.unb.mobiledev.saferide.DatabaseHelpers.LocationHelper

class ManualInput : AppCompatActivity() {

    private lateinit var dbHelper: LocationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manual_input)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userID = intent.getStringExtra("KEY_ID")?: "No User"

        if(userID == "No User"){
            Toast.makeText(this, "USER ID NOT HERE", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, userID, Toast.LENGTH_SHORT).show()
        }

        dbHelper = LocationHelper(this)

        val backButton: Button = findViewById(R.id.backButton)
        val submitButton: Button = findViewById(R.id.Submit)
        val dropdown: Spinner = findViewById(R.id.dropdown)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dropdown.adapter = adapter

        backButton.setOnClickListener {
            finish()
        }

        submitButton.setOnClickListener {
            val addressInput = findViewById<EditText>(R.id.AddressTextViewInput).text.toString()
            val selectedItem = dropdown.selectedItem.toString()
            if(selectedItem == "Select Station"){
                Toast.makeText(this, "Please select a valid station", Toast.LENGTH_SHORT).show()
            } else {
                val result = dbHelper.addLocation(userID, selectedItem, addressInput)
                if (result == -1L) {
                    Toast.makeText(this, "Failed to add location", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Location added", Toast.LENGTH_SHORT).show()
                }
            }
            //Log.i(TAG, "Input: $selectedItem")

        }

    }
}