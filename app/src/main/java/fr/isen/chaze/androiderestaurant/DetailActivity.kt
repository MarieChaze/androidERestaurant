package fr.isen.chaze.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.chaze.androiderestaurant.databinding.ActivityDetailsBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailTitle.text = intent.getStringExtra(CarteActivity.ITEM_KEY)

    }
}