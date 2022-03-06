package edu.miu.walmart

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.walmart.models.User

class RegisterActivity : AppCompatActivity() {
    private var listUser: ArrayList<User>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        listUser = intent.getSerializableExtra ("listUsers") as ArrayList<User>

        val loginBtn = findViewById<Button>(R.id.btnCreate)

        loginBtn.setOnClickListener{

            val txtInputFirstname = findViewById<TextView>(R.id.txtInputFirstname)
            val txtInputLastname = findViewById<TextView>(R.id.txtInputLastname)
            val txtInputEmail = findViewById<TextView>(R.id.txtInputEmail)
            val txtInputPassword = findViewById<TextView>(R.id.txtInputPassword)

            if (txtInputFirstname.text.isNullOrBlank()
                || txtInputLastname.text.isNullOrBlank()
                || txtInputEmail.text.isNullOrBlank()
                || txtInputPassword.text.isNullOrBlank()
            ) {
                Toast.makeText(this, "Please fill all requires fields", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }

            listUser!!.add(User(txtInputEmail.text.toString(), txtInputPassword.text.toString()));
            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
        }
    }
}