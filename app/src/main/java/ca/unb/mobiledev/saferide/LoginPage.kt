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
import android.nfc.Tag

class LoginPage : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button
    lateinit var signupButton : Button

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

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            val test : Boolean = MockDataMain.isAUser(username)
            if(test == true){
                Log.i(TAG, "Logged In Successfully!")
            }
            else{
                Log.i(TAG,"GTFO!")
            }
            Log.i("Test Credentials", "Username: $username and Password: $password")
        }
    }
}