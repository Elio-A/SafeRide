package ca.unb.mobiledev.saferide

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.viewmodels.UserViewModel
import ca.unb.mobiledev.saferide.viewmodels.UserViewModelFactory

class LoginPage : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var listView: ListView

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

        val factory = UserViewModelFactory(application)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        usernameInput = findViewById(R.id.login_username)
        passwordInput = findViewById(R.id.login_password)

        loginButton = findViewById(R.id.login_button)
        signupButton = findViewById(R.id.signup_button)

        signupButton.setOnClickListener{
            val studentId = 3725253
            val firstName = "Albertus"
            val lastName = "Koesoema"
            val email = "albertus.university@unb.ca"
            val driver = false

            userViewModel.insert(User(userId = studentId, firstName = firstName, lastName = lastName, email = email, driver = driver ))
        }

        loginButton.setOnClickListener {
            val studentId = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString()

            var intent = Intent(this@LoginPage, HomePageActivity::class.java)

            //call function from userRepository
            userViewModel.getUserById(studentId.toInt()).observe(this){ users ->
                if(users != null){
                    Log.i(TAG, "Logged In Successfully!")
                    startActivity(intent)
                }
                else{
                    Log.i(TAG,"GTFO!")
                }
            }
        }
    }
}