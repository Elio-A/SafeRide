package ca.unb.mobiledev.saferide

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarPhotos : AppCompatActivity() {

    private lateinit var frontButton: Button
    private lateinit var rightButton: Button
    private lateinit var backButton: Button
    private lateinit var leftButton: Button

    private lateinit var frontImageView: ImageView
    private lateinit var rightImageView: ImageView
    private lateinit var backImageView: ImageView
    private lateinit var leftImageView: ImageView

    private var frontImagePosted: Boolean = false
    private var rightImagePosted: Boolean = false
    private var leftImagePosted: Boolean = false
    private var backImagePosted: Boolean = false

    private var cameraActivityResultLauncher: ActivityResultLauncher<Intent>? = null

    private var currentImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_photos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nextButton: Button = findViewById(R.id.nextCarButton)
        nextButton.setOnClickListener {
            if(frontImagePosted && rightImagePosted && backImagePosted && leftImagePosted) {
                val intent = Intent(this, PickupStationsDriver::class.java)
                startActivity(intent)
            } else {
                Log.i(TAG, "Enter a Photo for each view")
            }
        }

        val backID: Button = findViewById(R.id.backButton)
        backID.setOnClickListener {
            finish()
        }

        frontButton = findViewById(R.id.ImageFront)
        frontImageView = findViewById(R.id.FrontImage)

        rightButton = findViewById(R.id.ImageRight)
        rightImageView = findViewById(R.id.RightImage)

        backButton = findViewById(R.id.ImageBack)
        backImageView = findViewById(R.id.BackImage)

        leftButton = findViewById(R.id.ImageLeft)
        leftImageView = findViewById(R.id.LeftImage)

        setCameraActivityResultLauncher()

        frontButton.setOnClickListener {
            currentImageView = frontImageView
            showImageSourceOptions()
        }

        rightButton.setOnClickListener {
            currentImageView = rightImageView
            showImageSourceOptions()
        }

        backButton.setOnClickListener {
            currentImageView = backImageView
            showImageSourceOptions()
        }

        leftButton.setOnClickListener {
            currentImageView = leftImageView
            showImageSourceOptions()
        }
    }

    private fun showImageSourceOptions(){
        val options = arrayOf("Take Photo", "Choose from Gallery")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Select Option")
        builder.setItems(options){ _, which ->
            when (which) {
                0 -> dispatchTakePictureIntent()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun setCameraActivityResultLauncher(){
        cameraActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val imageBitmap: Bitmap?
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null && data.extras?.containsKey("data") == true) {
                    imageBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        data.extras?.getParcelable("data", Bitmap::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        data.extras?.getParcelable("data")
                    }
                    currentImageView?.setImageBitmap(imageBitmap)
                }

                else if(data?.data != null){
                    val selectedImageUri: Uri? = data.data
                    currentImageView?.setImageURI(selectedImageUri)
                }
                currentImageView?.visibility = View.VISIBLE
                when (currentImageView){
                    frontImageView -> frontImagePosted = true
                    rightImageView -> rightImagePosted = true
                    backImageView -> backImagePosted = true
                    leftImageView -> leftImagePosted = true
                }
            }
        }
    }

    private fun dispatchTakePictureIntent(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.resolveActivity(packageManager)?.also{
            cameraActivityResultLauncher!!.launch(takePictureIntent)
        }
    }

    private fun openGallery(){
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        cameraActivityResultLauncher?.launch(pickPhotoIntent)
    }


}