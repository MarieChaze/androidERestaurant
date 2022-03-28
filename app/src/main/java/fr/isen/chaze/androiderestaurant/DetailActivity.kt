package fr.isen.chaze.androiderestaurant

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.chaze.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.chaze.androiderestaurant.model.Item

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    lateinit var quantity: TextView
    lateinit var plus: Button
    lateinit var minus: Button

    var counter: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(CategoryActivity.ITEM_KEY) as Item

        binding.detailTitle.text = item.name_fr
        binding.listeIngredients.text = item.ingredients.joinToString { it.name_fr }

        val carouselAdapter = CarouselAdapter(this, item.images)

        binding.detailSlider.adapter = carouselAdapter

        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        quantity = findViewById(R.id.quantity)
        quantity.text = counter.toString()
        doTotal(item,counter)

        plus.setOnClickListener {
            if (counter >= 0) {
                counter++
                quantity.text = counter.toString()
                doTotal(item,counter)
            }
        }
        minus.setOnClickListener {
            if (counter > 0) {
                counter--
                quantity.text = counter.toString()
                doTotal(item,counter)
            }
        }


    }

    private fun doTotal(item: Item, selected: Float) {
        val ttlPrix: String = item.prices[0].price
        val total: Float = ttlPrix.toFloat() * selected
        val totalString: String = "Total : " + total.toString() + "€"
        binding.ajout.text = totalString
    }

}