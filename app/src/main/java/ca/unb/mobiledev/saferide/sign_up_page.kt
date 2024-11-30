package ca.unb.mobiledev.saferide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class sign_up_page : AppCompatActivity() {

    private lateinit var usernameEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var confirmPasswordEditText : EditText
    private lateinit var signupButton : Button
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var isDriverCheckbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usernameEditText = findViewById(R.id.signup_username)
        passwordEditText = findViewById(R.id.signup_password)
        confirmPasswordEditText = findViewById(R.id.confirm_password)
        signupButton = findViewById(R.id.signup_button)
        dbHelper = DatabaseHelper(this)
        isDriverCheckbox = findViewById(R.id.is_driver_checkbox)

        signupButton.setOnClickListener {
            intent = Intent(this@sign_up_page, LoginPage::class.java)
            confirmCompitentInput(intent)
        }
    }

    private fun confirmCompitentInput(intent : Intent){
        val username : String = usernameEditText.text.toString()
        val password : String = passwordEditText.text.toString()
        val confirmPassword : String = confirmPasswordEditText.text.toString()
        val isDriver: Boolean = isDriverCheckbox.isChecked

        if(password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty()){
            Toast.makeText(this, "Please fill in the empty field(s)!", Toast.LENGTH_SHORT).show()
        }
        else if(password == confirmPassword){
            val result = dbHelper.addUser(username, password, isDriver)
            if(result != -1L) {
                Toast.makeText(this, "User Created Successfully!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Failed to create user.  Username may already exist.", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Please ensure the passwords you entered match each other!", Toast.LENGTH_SHORT).show()
        }
    }
}//End SignupPage