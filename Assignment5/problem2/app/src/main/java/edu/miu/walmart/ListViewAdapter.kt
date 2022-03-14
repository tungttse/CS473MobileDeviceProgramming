package edu.miu.walmart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edu.miu.walmart.models.Product

class ListViewAdapter(private val context: Context,
                      private val dataSource: ArrayList<Product>): BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView: View?
        if (convertView == null) {
            // get layout from mobile.xml
            itemView = inflater.inflate(R.layout.product_item, null)

            // set image based on selected text
            val imageView: ImageView = itemView
                .findViewById(R.id.productImg) as ImageView
            imageView.setImageResource(dataSource.get(position).image)

//            imageView.setOnClickListener {
//                Toast.makeText(context, "You have chosen the ${dataSource.get(position).name} category of shopping", Toast.LENGTH_SHORT).show()
//            }

            val titleText: TextView = itemView.findViewById(R.id.productTitle) as TextView
            titleText.text = dataSource.get(position).title

            val colorTextView: TextView = itemView?.findViewById(R.id.productColor) as TextView
            print("TUNTT" + colorTextView.toString());

            colorTextView.text = dataSource.get(position).color

            val priceTextView: TextView = itemView
                .findViewById(R.id.productPrice) as TextView
            priceTextView.text = dataSource.get(position).price.toString()

        } else {
            itemView = convertView as View?
        }
        return itemView
    }
}