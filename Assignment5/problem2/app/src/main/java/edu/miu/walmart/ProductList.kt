package edu.miu.walmart

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.miu.walmart.adapters.ProductListAdapter
import edu.miu.walmart.models.Product


class ProductList : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        recyclerView = findViewById<RecyclerView>(R.id.productList)
        val listItems =  arrayListOf(
            Product("RGA Voyager 7\" 16GB Android Tablet", 35.00, "Black", R.drawable.p1, "Rct6873w42", "Introducing the RCA Voyager 7-inch Google Certified Tablet. Operating on Android 8.1, you can enjoy more of what the Google Play store has to offer. Share a smile with friends and family using the tablet's front camera. Use the tablet's built-in Bluetooth capability to connect and pair with compatible devices. Whether taking in a movie, playing a favorite game or surfing the Web, the RCA tablet's quad-core processor and 16GB flash-storage surely won't disappoint."),
            Product("HP Flyer Red 15.6\" Laptop", 299.0, "Black & Red", R.drawable.p2, "1A493UA", "HP Flyer Red 15.6\" 15-f272wm Laptop PC with Intel Pentium N3540 Processor, 4GB Memory, 500GB Hard Drive and Windows 10 Home"),
            Product("VIZIO 70\" Class 4K", 1298.00, "Black", R.drawable.p3, "546665868", "Escape into awesome entertainment with the VIZIO V-Series 4K UHD Smart TV. With epic 4K UHD picture quality, Dolby Vision HDR and HDR10, a full array backlight, and active pixel tuning, V-Series provides crystal-clear and sharp picture quality. The next-gen speed and power of the VIZIO IQ Active processor brings astonishing detail and vibrancy to your viewing experience. Take your gaming to a whole new level with the V-Gaming engine which enables sub 10ms input lag, 4K 60fps Variable Refresh Rate and Auto Game Mode. Stream it all with the award-winning SmartCast platform loaded with the best selection of built-in apps including WatchFree+ that lets you enjoy free access to live TV and hundreds of free channels right out of the box. SmartCast also works with Apple AirPlay 2 and Chromecast built-in, offering unparalleled entertainment in a size that fits your lifestyle. V-series is everything you need and more in a Smart TV delivering a standout experience at an unmatched price."),
            Product("LG 75\" Class 4K Ultra HD 2160P Smart TV with HDR 75UP7070PUD", 796.99, "Black", R.drawable.p4, "546665868", "Escape into awesome entertainment with the VIZIO V-Series 4K UHD Smart TV. With epic 4K UHD picture quality, Dolby Vision HDR and HDR10, a full array backlight, and active pixel tuning, V-Series provides crystal-clear and sharp picture quality. The next-gen speed and power of the VIZIO IQ Active processor brings astonishing detail and vibrancy to your viewing experience. Take your gaming to a whole new level with the V-Gaming engine which enables sub 10ms input lag, 4K 60fps Variable Refresh Rate and Auto Game Mode. Stream it all with the award-winning SmartCast platform loaded with the best selection of built-in apps including WatchFree+ that lets you enjoy free access to live TV and hundreds of free channels right out of the box. SmartCast also works with Apple AirPlay 2 and Chromecast built-in, offering unparalleled entertainment in a size that fits your lifestyle. V-series is everything you need and more in a Smart TV delivering a standout experience at an unmatched price."),
            Product("Magic Keyboard for iPad Pro 12.9‑inch (5th generation) - Black", 339.99, "Black", R.drawable.p5, "332088338", "The Magic Keyboard is an amazing companion for iPad Pro and iPad Air. It features an incredible typing experience, a trackpad that opens up new ways to work with iPadOS, a USB-C port for passthrough charging, and front and back protection. The Magic Keyboard has a floating cantilever design, allowing you to attach iPad Pro and iPad Air magnetically and to smoothly adjust it to the perfect viewing angle for you."),
        );
        val adapter = ProductListAdapter(listItems) { product -> adapterOnClick(product) };
        recyclerView.adapter = adapter

//        recyclerView.onItemClickListener =
//            OnItemClickListener { parent, view, position, id ->
//                val product: Product = listView.getItemAtPosition(position) as Product
//                val intent = Intent(this, ProductDetail::class.java)
//                intent.putExtra("product", product);
//                startActivity(intent)
//            }
    }

    /* Opens ProductDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(product: Product) {
        val intent = Intent(this, ProductDetail::class.java)
        intent.putExtra("product", product);
        startActivity(intent)
    }
}