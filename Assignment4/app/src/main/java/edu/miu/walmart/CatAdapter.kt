package edu.miu.walmart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

            imageView.setOnClickListener {
                Toast.makeText(context, "You have chosen the ${dataSource.get(position).name} category of shopping", Toast.LENGTH_SHORT).show()
            }

            val textView: TextView = gridView
                .findViewById(R.id.catName) as TextView
            textView.text = dataSource.get(position).name

        } else {
            gridView = convertView as View?
        }

        return gridView
    }

//    protected fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val heightSpec: Int
//        heightSpec = if (getLayoutParams().height === ViewGroup.LayoutParams.WRAP_CONTENT) {
//            // The great Android "hackatlon", the love, the magic.
//            // The two leftmost bits in the height measure spec have
//            // a special meaning, hence we can't use them to describe height.
//            MeasureSpec.makeMeasureSpec(
//                Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST
//            )
//        } else {
//            // Any other height should be respected as is.
//            heightMeasureSpec
//        }
//        super.onMeasure(widthMeasureSpec, heightSpec)
//    }
}