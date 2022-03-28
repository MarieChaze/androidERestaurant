package fr.isen.chaze.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.chaze.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.chaze.androiderestaurant.model.DataResult
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var items: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        items = arrayListOf()
        val categorieName = intent.getStringExtra("category")
        binding.category.text = categorieName

        //recycler view gérer une liste d'éléments

        afficheEntrees()

        getDataFromApi(categorieName ?: "")
        Log.d("LogApi", items.toString())
    }

    // ternaire affecter une valuer en fonction d'une condiation true or false par exemple ?: est un ternaire
    //?: si l'élément que je test est diff de nul alors je renvoie un élément sinon je renvoir une arrayList

    fun afficheEntrees(){
        //val entreesList = resources.getStringArray(R.array.entreesList).toList()
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CategoryAdapter(arrayListOf()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(ITEM_KEY, it)
            startActivity(intent)
        }
    }
    companion object{
        val ITEM_KEY = "item"
    }

    private fun getDataFromApi(category: String){
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val json = JSONObject()
        json.put("id_shop", "1")
        json.toString()
        val request = JsonObjectRequest( Request.Method.POST,url,json,
            Response.Listener { response ->
                val strResp = response.toString()
                val data = Gson().fromJson(strResp, DataResult::class.java)
                val items = data.data.firstOrNull{it.name_fr == category}?.items ?: arrayListOf()
                binding.recyclerView.adapter = CategoryAdapter(items) {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(ITEM_KEY, it)
                    startActivity(intent)
                }
            },  {
                Log.e(TAG, "Log Volley error: $it" )

            })
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }
    private val TAG = "LogCategorieActivity"

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Sortie de la page des categories")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Page des catégories détruite")
    }
}
