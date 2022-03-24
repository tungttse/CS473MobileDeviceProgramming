package edu.miu.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)

        var result = 0;

        var radioGroup = findViewById<RadioGroup>(R.id.groupRadio);
        if(radioGroup!=null) {
            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    R.id.a1 -> result = result + 50;
                    R.id.a2 -> result = result
                    else ->  result = result
                }
            }
        }

        var checkBox1:CheckBox =  findViewById<CheckBox>(R.id.c1);
        var checkBox2:CheckBox =  findViewById<CheckBox>(R.id.c2);
        var checkBox3:CheckBox =  findViewById<CheckBox>(R.id.c3);
        var checkBox4:CheckBox =  findViewById<CheckBox>(R.id.c4);

        checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                result = result + 50;
            }
        }

        submitBtn.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            showDialog("You submitted on " + currentDate + " Your achieved " + result.toString() + "%");
        }

        resetBtn.setOnClickListener {
            radioGroup?.clearCheck();
            result = 0;
            checkBox1.isChecked = false;
            checkBox2.isChecked = false;
            checkBox3.isChecked = false;
            checkBox4.isChecked = false;
        }
    }

    fun showDialog(message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("Congratulations!")
            .setMessage(message)

        builder.setPositiveButton(android.R.string.ok) { _, _ -> }
        builder.show()
    }
}
