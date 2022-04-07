package miu.edu.cvbuilderapp.ui.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cvbuilderapp.R
import miu.edu.cvbuilderapp.model.User

class LoginActivity : AppCompatActivity() {
    var listUsers = arrayListOf(
        User("user1@gmail.com", "123")
    );

    var isAllFieldsChecked = false
    lateinit var txtInputEmail: TextView ;
    lateinit var txtInputPassword: TextView ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref = this.getSharedPreferences("cv_builder_app", Context.MODE_PRIVATE)

        val loginBtn = findViewById<Button>(R.id.btnCreate)

        loginBtn.setOnClickListener{
            txtInputEmail = findViewById<TextView>(R.id.txtInputEmail)
            txtInputPassword = findViewById<TextView>(R.id.txtInputPassword)

            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                val isCredential = listUsers.contains(User(txtInputEmail.text.toString(), txtInputPassword.text.toString()));
                if(isCredential) {
                    // using shared preference to store the email and login status.
                    with (sharedPref.edit()) {
                        putString("is_login", "true") // put status login
                        putString("name", "Thanh Tung") // put first name
                        apply()
                    }

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show();
                }
            }
        }

        val forgetPass = findViewById<TextView>(R.id.forgetPass)
        forgetPass.setOnClickListener {
            val txtInputEmail = findViewById<TextView>(R.id.txtInputEmail)
            if(txtInputEmail.text.isNullOrBlank()) {
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val foundUser = listUsers.find { u -> u.email === txtInputEmail.text.toString() }
            if(foundUser!= null) {
                sendEmail(foundUser.email, "Forget Password", "Your password is" + foundUser.password)
            } else {
                Toast.makeText(this, "Not user found", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun CheckAllFields(): Boolean {
        if (txtInputEmail.text.isNullOrEmpty()) {
            txtInputEmail.setError("This field is required")
            return false
        }
        if (txtInputPassword.text.isNullOrEmpty()) {
            txtInputPassword.setError("This field is required")
            return false
        }
        // after all validation return true.
        return true
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, recipient)
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}