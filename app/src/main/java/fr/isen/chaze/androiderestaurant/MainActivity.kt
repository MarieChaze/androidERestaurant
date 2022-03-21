package fr.isen.chaze.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Toast Entrées
        val Plats = findViewById(R.id.buttonPlats) as Button
        val Entrees = findViewById(R.id.buttonEntrees) as Button
        val Desserts = findViewById(R.id.buttonDessert) as Button

        Entrees.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Vous avez cliqué sur 'Entrées'",
                Toast.LENGTH_SHORT
            ).show()
        }

        Plats.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Vous avez cliqué sur 'Plats'",
                Toast.LENGTH_SHORT
            ).show()
        }

        Desserts.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Vous avez cliqué sur 'Desserts'",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}