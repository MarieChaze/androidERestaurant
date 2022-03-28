package fr.isen.chaze.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.chaze.androiderestaurant.model.Item

class BLEscanAdapter (val data: ArrayList<Item>, val clickListener: (Item) -> Unit) : RecyclerView.Adapter<BLEscanAdapter.CategoryViewHolder>() {

        inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var deviceTitle: TextView = view.findViewById(R.id.deviceTitle)

            var priceTextView: TextView = view.findViewById(R.id.priceTextView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val deviceView = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.device,
                    parent,
                    false
                )
            return CategoryViewHolder(deviceView)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val item = data[position]
            holder.deviceTitle.text = item.name_fr

            Picasso.get().load(item.images[0].ifEmpty{ null })
                .placeholder(R.drawable.ic_launcher_foreground)
            holder.itemView.setOnClickListener { clickListener(item) }



        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}
