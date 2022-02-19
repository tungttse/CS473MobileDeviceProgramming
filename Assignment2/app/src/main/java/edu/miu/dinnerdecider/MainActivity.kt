package edu.miu.dinnerdecider

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var listFoods = arrayListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectFoodBtn = findViewById<Button>(R.id.selectFoodBtn)
        val addFoodBtn = findViewById<Button>(R.id.addFoodBtn)

        val foodSelectedTxt = findViewById<TextView>(R.id.foodSelectedTxt)
        foodSelectedTxt.text = listFoods.get(0);

        val addFoodTxt = findViewById<TextView>(R.id.addFoodTxt)

        selectFoodBtn.setOnClickListener {
            val listLen = listFoods.size - 1;
            val ranNum = (0..listLen).random();
            foodSelectedTxt.text = listFoods[ranNum];
        }

        addFoodBtn.setOnClickListener(View.OnClickListener {
            if(addFoodTxt.text.isBlank()){
                return@OnClickListener;
            }
            listFoods.add(addFoodTxt.text.toString());
            foodSelectedTxt.text = addFoodTxt.text.toString();
        })
    }
}