package ca.unb.mobiledev.saferide

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class stations : AppCompatActivity() {
    private var isImageVisible = false
    private lateinit var dropDownButton1 : Button
    private lateinit var dropDownImage1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stations)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backToHome : ImageButton = findViewById(R.id.back_to_home_button)
        backToHome.setOnClickListener{
            finishAffinity()
        }

        dropDownImage1 = findViewById(R.id.drop_down_image_1)
        dropDownButton1 = findViewById(R.id.drop_down_button_1)
        dropDownButton1.setOnClickListener{
            toggleImage()
        }
    }

    private fun toggleImage(){
        if(isImageVisible == true){
            dropDownImage1.animate()
                .translationY(-dropDownImage1.height.toFloat())
                .alpha(0f)
                .setDuration(300)
                .withEndAction{
                    dropDownImage1.visibility = View.GONE
                }
                .start()
        }
        else{
            dropDownImage1.visibility = View.VISIBLE
            dropDownImage1.translationY = -dropDownImage1.height.toFloat()
            dropDownImage1.alpha = 0f
            dropDownImage1.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(300)
                .start()
        }
        isImageVisible = !isImageVisible
    }
}