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
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import ca.unb.mobiledev.saferide.entity.User
import ca.unb.mobiledev.saferide.viewmodels.UserViewModel
import java.util.concurrent.Future

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

        usernameInput = findViewById(R.id.login_username)
        passwordInput = findViewById(R.id.login_password)

        loginButton = findViewById(R.id.login_button)
        signupButton = findViewById(R.id.signup_button)

        signupButton.setOnClickListener{
            val studentId = 3725253
            val firstName = "Albertus"
            val lastName = "Koesoema"
            val email = "albertus.university@unb.ca"
            val password = "albert"
            val driver = false
            addUser(studentId, firstName, lastName, email, password, driver)
            Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
        //userViewModel.insert(User(userId = studentId, firstName = firstName, lastName = lastName, email = email, driver = driver ))
        }

        loginButton.setOnClickListener {
            val studentId = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            searchForUser(studentId.toInt(), password)
            //call function from userRepository

        }
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    private fun addUser(studentId: Int, firstName: String, lastName: String, email: String, password: String, driver: Boolean){
        userViewModel.insert(studentId, firstName, lastName, email, password, driver)
    }

    private fun searchForUser(studentId: Int, password: String){
        val user: Future<List<User>> = userViewModel.getUserById(studentId, password)
        if(user != null){
            Log.i(TAG, "Logged In Successfully!")
            var intent = Intent(this@LoginPage, HomePageActivity::class.java)
            startActivity(intent)
        }
        else{
            Log.i(TAG,"GTFO!")
        }
    }
}