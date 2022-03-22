package fr.isen.chaze.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.chaze.androiderestaurant.databinding.ActivityCarteBinding

class CarteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCarteBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityCarteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.category.text = intent.getStringExtra("category") ?: ""
        val items = resources.getStringArray(R.array.plats)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CategoryAdapter(items) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(ITEM_KEY, it)
            startActivity(intent)
        }


    }
    companion object {
        val ITEM_KEY = "item"
    }

}
