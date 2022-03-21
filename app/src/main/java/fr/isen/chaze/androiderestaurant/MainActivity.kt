package fr.isen.chaze.androiderestaurant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.chaze.androiderestaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

      //  setContentView(R.layout.activity_main)


        //Toast Entrées
     //   val Plats = findViewById(R.id.buttonPlats) as Button
     //   val Entrees = findViewById(R.id.buttonEntrees) as Button
     //   val Desserts = findViewById(R.id.buttonDessert) as Button

        //toast pour le bouton entrée
/*       Entrees.setOnClickListener {
          Toast.makeText(
              this@MainActivity,
              "Vous avez cliqué sur 'Entrées'",
              Toast.LENGTH_SHORT
          ).show()
      }
    //toast pour le bouton plat
      Plats.setOnClickListener {
          Toast.makeText(
              this@MainActivity,
              "Vous avez cliqué sur 'Plats'",
              Toast.LENGTH_SHORT
          ).show()
      }

     //toast pour le bouton dessert
         Desserts.setOnCliistener {
          Toast.makeText(
              this@MainActivity,
              "Vous avez cliqué sur 'Desserts'",
              Toast.LENGTH_SHORT
          ).show()
      } */

      binding.buttonEntrees.setOnClickListener{
          val intent = Intent(this, Page_Entrees::class.java)
          startActivity(intent)

      }
        binding.buttonPlats.setOnClickListener{
            val intent = Intent(this, Page_Plat::class.java)
          //  intent.putExtra() //pour avoir une page au lieu de plusieurs.
            startActivity(intent)

        }

        binding.buttonDessert.setOnClickListener{
            val intent = Intent(this, Page_Dessert::class.java)
            startActivity(intent)

        }


  }
}