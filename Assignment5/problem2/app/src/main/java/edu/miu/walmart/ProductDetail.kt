package edu.miu.walmart

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import edu.miu.walmart.models.Product
import edu.miu.walmart.models.User

class ProductDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val extras = intent.extras
        if (extras != null) {
            val product = intent.getSerializableExtra ("product") as Product
            println("product " + product.title);

            val image = findViewById<ImageView>(R.id.image)
            image.setBackgroundResource(product.image);

            val title = findViewById<TextView>(R.id.title)
            title.text = product.title

            val color = findViewById<TextView>(R.id.color)
            color.text = "Color: " + product.color

            val price = findViewById<TextView>(R.id.price)
            price.text = "Price: " + product.price + "$"

            val itemid = findViewById<TextView>(R.id.itemid)
            itemid.text = "Walmart #: " + product.itemid

            val description = findViewById<TextView>(R.id.description)
            description.text = product.desc
        }
    }
}