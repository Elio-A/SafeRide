package ca.unb.mobiledev.saferide

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import android.Manifest
import android.net.Uri
import android.util.Patterns

class HomePageActivity : AppCompatActivity(){

    companion object{
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val stationButton : Button = findViewById(R.id.pickup_stations_button)
        val radiusButton : Button = findViewById(R.id.radius_button)
        val workingHourButton : Button = findViewById(R.id.working_hours_button)
        val aboutUsButton : Button = findViewById(R.id.about_us_button)
        val qrCodeScanner : Button = findViewById(R.id.qr_code_button)
        val nextPickupButton: Button = findViewById(R.id.next_pickup_button)
        val newsButton: Button = findViewById(R.id.news_button)
        val driverHomePage: Button = findViewById(R.id.driver_home_page)
        val manualInput: Button = findViewById(R.id.manual_input)

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

        qrCodeScanner.setOnClickListener{
            requestCameraPermission()
        }

        newsButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.unbsu.ca/saferide")
            startActivity(intent)
        }

        nextPickupButton.setOnClickListener {
            val intent = Intent(this@HomePageActivity, Next_Pickup::class.java)
            startActivity(intent)
        }

        driverHomePage.setOnClickListener {
            val intent = Intent(this@HomePageActivity, DriverHomePage::class.java)
            startActivity(intent)
        }

        manualInput.setOnClickListener{
            val intent = Intent(this@HomePageActivity, ManualInput::class.java)
            startActivity(intent)
        }
    }

    private fun requestCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
        else{
            initiateQRScanner()
        }
    }

    private fun initiateQRScanner(){
        val integrator = IntentIntegrator(this).apply{
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            setPrompt("Scan a QR Code")
            setCameraId(0)
            setBeepEnabled(true)
            setBarcodeImageEnabled(true)
        }
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null){
                Toast.makeText(this, "Scan Cancelled!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Scanned Successfully!", Toast.LENGTH_SHORT).show()
                handleScannedContent(result.contents)
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleScannedContent(scannedContent: String) {
        if(Patterns.WEB_URL.matcher(scannedContent).matches()){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scannedContent))
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "I do not know what this is yet, gotta deal with it!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initiateQRScanner()
            }
            else{
                Toast.makeText(this, "Camera permission is required to scan QR codes!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed(){
        AlertDialog.Builder(this)
            .setTitle("Confirm Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
                intent = Intent(this@HomePageActivity, LoginPage::class.java)
                startActivity(intent)
                finishAffinity()
            }
            .setNegativeButton("No", null)
            .show()
    }
}//End HomePageActivity