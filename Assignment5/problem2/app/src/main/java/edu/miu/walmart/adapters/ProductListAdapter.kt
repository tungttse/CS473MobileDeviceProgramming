package edu.miu.walmart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.miu.walmart.R
import edu.miu.walmart.models.Product

class ProductListAdapter(private val dataSource: ArrayList<Product>, private val onClick: (Product) -> Unit)
:RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    class ViewHolder(view: View, val onClick: (Product) -> Unit) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.productImg) as ImageView
        val titleText: TextView = view.findViewById(R.id.productTitle) as TextView
        val colorTextView: TextView = view.findViewById(R.id.productColor) as TextView
        val priceTextView: TextView = view.findViewById(R.id.productPrice) as TextView
        private var currentProduct: Product? = null
        init {
            view.setOnClickListener {
                currentProduct?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(product: Product) {
            currentProduct = product
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item, viewGroup, false)
        return ViewHolder(view, onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.imageView.setImageResource(dataSource[position].image)
        viewHolder.titleText.text = dataSource[position].title.toString();
        viewHolder.colorTextView.text = "Color: "+ dataSource[position].color.toString()
        viewHolder.priceTextView.text = "Price: $" + dataSource[position].price.toString()
        viewHolder.bind(dataSource[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSource.size
}