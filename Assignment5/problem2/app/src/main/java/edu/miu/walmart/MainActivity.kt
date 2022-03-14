package edu.miu.walmart

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.walmart.models.User

class MainActivity : AppCompatActivity() {
    var listUsers = arrayListOf(
        User("user1@gmail.com", "123"),
        User("user2@gmail.com", "123"),
        User("user3@gmail.com", "123"),
        User("user4@gmail.com", "123"),
        User("user5@gmail.com", "123")
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.btnCreate)
        val registerBtn = findViewById<Button>(R.id.btnCreateNew)

        loginBtn.setOnClickListener{
            val txtInputEmail = findViewById<TextView>(R.id.txtInputEmail)
            val txtInputPassword = findViewById<TextView>(R.id.txtInputPassword)

            val isCredential = listUsers.contains(User(txtInputEmail.text.toString(), txtInputPassword.text.toString()));
            if(isCredential) {
                val intent = Intent(this, ShoppingCategory::class.java)
                intent.putExtra("email", txtInputEmail.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show();
            }
        }

        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("listUsers", listUsers)
            startActivity(intent)
        }

        val forgetPass = findViewById<TextView>(R.id.forgetPass)
        forgetPass.setOnClickListener {
            val txtInputEmail = findViewById<TextView>(R.id.txtInputEmail)
            if(txtInputEmail.text.isNullOrBlank()) {
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val foundUser = listUsers.find { u -> u.username === txtInputEmail.text.toString() }
            if(foundUser!= null) {
                sendEmail(foundUser.username, "Forget Password", "Your password is" + foundUser.password)
            } else {
                Toast.makeText(this, "Not user found", Toast.LENGTH_LONG).show()
            }
        }
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