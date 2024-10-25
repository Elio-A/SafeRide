package ca.unb.mobiledev.saferide

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class DriverHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_driver_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val qrCodeImageView: ImageView = findViewById(R.id.qr_code_image)

        val content = "https://www.youtube.com/shorts/SXHMnicI6Pg"
        val width = 500
        val height = 500

        val qrCodeBitmap = generateQRCode(content, width, height)
        qrCodeImageView.setImageBitmap(qrCodeBitmap)
    }

    //QRCode generation method
    private fun generateQRCode(content: String, width: Int, height: Int): Bitmap {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for(x in 0 until width){
            for(y in 0 until height){
                bitmap.setPixel(x,y, if(bitMatrix[x, y])
                android.graphics.Color.BLACK else android.graphics.Color.TRANSPARENT)
            }
        }
        return bitmap;
    }
}