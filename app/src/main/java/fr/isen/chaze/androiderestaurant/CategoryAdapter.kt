package fr.isen.chaze.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.chaze.androiderestaurant.model.Item

class CategoryAdapter(val data: ArrayList<Item>, val clickListener: (Item) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTitle: TextView = view.findViewById(R.id.itemTitle)
        var itemLogo: ImageView = view.findViewById(R.id.itemLogo)
        var priceTextView: TextView = view.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item,
                parent,
                false
            )
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = data[position]
        holder.itemTitle.text = item.name_fr

        val prix: String = item.prices[0].price+ "€"
        holder.priceTextView.text = prix
        Picasso.get().load(item.images[0].ifEmpty{ null })
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemLogo)
        holder.itemView.setOnClickListener { clickListener(item) }



    }

    override fun getItemCount(): Int {
        return data.size
    }
}
