package ca.unb.mobiledev.saferide

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginPage : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button
    lateinit var signupButton : Button
    var studentID: Int = 0
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

            val loginResult = dbHelper.checkUser(username, password)


            if(loginResult.first){
                Toast.makeText(this, "Logged In Successfully!", Toast.LENGTH_SHORT).show()
                val intent = if(loginResult.second){
                    Intent(this@LoginPage, SafeRideCars::class.java)
                }
                else{
                    Intent(this@LoginPage, HomePageActivity::class.java)
                }
                intent.putExtra("KEY_ID", username)
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
        }
    }

    override fun onBackPressed(){
        AlertDialog.Builder(this)
            .setTitle("Confirm Exit")
            .setMessage("Are you sure you want to exit the app?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
                finishAffinity()
            }
            .setNegativeButton("No", null)
            .show()
    }
}//End LoginPage