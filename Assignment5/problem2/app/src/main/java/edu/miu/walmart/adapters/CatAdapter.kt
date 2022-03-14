package edu.miu.walmart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import edu.miu.walmart.R
import edu.miu.walmart.models.Category


class CatAdapter(private val context: Context,
                 private val dataSource: ArrayList<Category>): BaseAdapter() {

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
        var gridView: View?
        if (convertView == null) {
            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.category_item, null)

            // set image based on selected text
            val imageView: ImageView = gridView
                .findViewById(R.id.catImg) as ImageView
            imageView.setImageResource(dataSource.get(position).thumbImg)

//            imageView.setOnClickListener {
//                Toast.makeText(context, "You have chosen the ${dataSource.get(position).name} category of shopping", Toast.LENGTH_SHORT).show()
//            }

            val textView: TextView = gridView
                .findViewById(R.id.catName) as TextView
            textView.text = dataSource.get(position).name
        } else {
            gridView = convertView as View?
        }
        return gridView
    }

}