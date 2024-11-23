package ca.unb.mobiledev.saferide

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toast
import ca.unb.mobiledev.saferide.DatabaseHelpers.UserDatabaseHelper

class LoginPage : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button
    lateinit var signupButton : Button
    private lateinit var dbHelper: UserDatabaseHelper

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

        dbHelper = UserDatabaseHelper(this)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            var intent = Intent(this@LoginPage, HomePageActivity::class.java)


            if(dbHelper.checkUser(username, password)){
                Toast.makeText(this, "Logged In Successfully!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Username or Password incorrect, please try again!", Toast.LENGTH_SHORT).show()
            }
            Log.i("Test Credentials", "Username: $username and Password: $password")
        }

        signupButton.setOnClickListener {
            intent = Intent(this@LoginPage, sign_up_page::class.java)
            startActivity(intent)
            finish()
        }
    }
}//End LoginPage