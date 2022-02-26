package edu.miu.assignment3

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val mainTable = findViewById<TableLayout>(R.id.table)

        btnAdd.setOnClickListener {
            val txtVersion = findViewById<TextView>(R.id.androidVersion)
            val txtCodeName = findViewById<TextView>(R.id.androidCodeName)

            //validate data is null or blank
            if (txtVersion.text.isNullOrBlank() || txtCodeName.text.isNullOrBlank()) return@setOnClickListener

            //crate new row programmatically
            val tableRow = TableRow(applicationContext)
            val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
            tableRow.layoutParams = layoutParams

            val tvVersion = TextView(applicationContext)
            val tvCodeName = TextView(applicationContext)

            val tvTemplate = findViewById<TextView>(R.id.txtTemplate)

            tvVersion.layoutParams = tvTemplate.layoutParams
            tvVersion.background = ContextCompat.getDrawable(applicationContext, R.drawable.border)
            tvVersion.setPadding(10)

            tvCodeName.layoutParams = tvTemplate.layoutParams
            tvCodeName.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.border)
            tvCodeName.setPadding(10)

            tvVersion.text = txtVersion.text
            tvCodeName.text = txtCodeName.text

            tableRow.addView(tvVersion)
            tableRow.addView(tvCodeName)
            mainTable.addView(tableRow)
        }
    }
}