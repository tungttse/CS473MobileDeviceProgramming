package edu.miu.walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import edu.miu.walmart.models.Product
import edu.miu.walmart.models.User

class ProductList : AppCompatActivity() {
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        listView = findViewById<ListView>(R.id.productList)
        val listItems =  arrayListOf(
            Product("Title1", 12.30, "red", R.drawable.p1, "", ""),
            Product("Title1", 12.30, "red", R.drawable.p1, "", ""),
            Product("Title1", 12.30, "red", R.drawable.p1, "", ""),
        );
        val adapter = ListViewAdapter(this, listItems);
        listView.adapter = adapter
    }
}