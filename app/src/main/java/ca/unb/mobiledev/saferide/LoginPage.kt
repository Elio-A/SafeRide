package ca.unb.mobiledev.saferide

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import MockData.*
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.viewmodels.UserViewModel
import java.util.concurrent.Future
import kotlin.math.sign

class LoginPage : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button
    lateinit var signupButton : Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usernameInput = findViewById(R.id.login_username)
        passwordInput = findViewById(R.id.login_password)

        loginButton = findViewById(R.id.login_button)
        signupButton = findViewById(R.id.signup_button)

        dbHelper = DatabaseHelper(this)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            val loginResult = dbHelper.checkUser(username, password)


            if(loginResult.first){
                Toast.makeText(this, "Logged In Successfully!", Toast.LENGTH_SHORT).show()

                val intent = if(loginResult.second){
                    Intent(this@LoginPage, PickupStationsDriver::class.java)
                }
                else{
                    Intent(this@LoginPage, HomePageActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Username or Password incorrect, please try again!", Toast.LENGTH_SHORT).show()
            }
        }

        signupButton.setOnClickListener {
            intent = Intent(this@LoginPage, sign_up_page::class.java)
            startActivity(intent)
            finish()
        }
    }
}//End LoginPage