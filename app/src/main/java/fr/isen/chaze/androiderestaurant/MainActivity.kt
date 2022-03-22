package fr.isen.chaze.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.chaze.androiderestaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonEntrees.setOnClickListener {
            goToCategory(getString(R.string.entrees))

        }

        binding.buttonPlats.setOnClickListener {
            goToCategory(getString(R.string.plats))

        }
        binding.buttonDesserts.setOnClickListener {
            goToCategory(getString(R.string.desserts))

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "Mon activité est détruite") //.d = débug, .e = error, .i = info
    }


    private fun goToCategory(category: String) {
        val intent = Intent(this, CarteActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }

}


