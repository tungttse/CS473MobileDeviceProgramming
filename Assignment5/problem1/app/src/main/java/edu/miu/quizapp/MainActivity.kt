package edu.miu.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var radioGroup
            : RadioGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)

        showDialog("You submitted on 2022-03-14 Your achieved 50%");

//        var result = 0;
//        radioGroup = findViewById<RadioButton>(R.id.myRadioGroup);
//        if(radioGroup!=null) {
//            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
//                when (radioGroup.getCheckedRadioButtonId()) {
//                    R.id.a1 -> result = result + 50;
//                    R.id.a2 -> result = result
//                    else ->  result = result
//                }
//            }
//        }
//
//        var checkBox:CheckBox = CheckBox(context)
//        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                result = result + 50;
//            }
//        }
//
//        submitBtn.setOnClickListener {
//            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//            val currentDate = sdf.format(Date())
//            showDialog("“Congratulations! You submitted on " + currentDate + " Your achieved " + result + "%");
//        }
//
//        resetBtn.setOnClickListener {
//            radioGroup.clearCheck();
//            result = 0;
//        }
    }

    fun showDialog(message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("Congratulations!")
            .setMessage(message)

        builder.setPositiveButton(android.R.string.ok) { _, _ -> }
        builder.show()
    }
}
