package top.stores.ecommerceapp

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*
import org.json.JSONException
import top.stores.ecommerceapp.RoomDB.DatabaseClient
import top.stores.ecommerceapp.RoomDB.Recipe
import top.stores.ecommerceapp.databinding.ActivityThirdBinding
import top.stores.ecommerceapp.repository.Repo


class ThirdActivity : AppCompatActivity() {
    var recipes: List<Repo>? = null
    var urlToArray = "https://api.androidhive.info/json/shimmer/menu.php"
    private var arrayList: ArrayList<Repo>? = null
    private lateinit var  binding : ActivityThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_third)
        //getGsonObjectRequest()
        //getGsonArrayRequest()
        getGsonArrayRequest()
    }


       fun getGsonObjectRequest(){
           buttonSearchEmployees.setOnClickListener {

               var url  : String = "https://api.androidhive.info/json/shimmer/menu.php" + editTestSearch.text.toString()
               var rq : RequestQueue = Volley.newRequestQueue(this)
               var jor = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->

                   textViewShowName.text = response.getString("name")
                   textViewShowSalary.text = response.getString("description")



               }, Response.ErrorListener { error ->
                   textViewShowName.text = error.message
                   textViewShowSalary.text = error.message

               })
               rq.add(jor)
           }
       }

    fun getGsonArrayRequest(){


        var urlToArray = "https://pastebin.com/raw/Em972E5s"
        var list = ArrayList<String>()
        var result:String = ""

        var rq : RequestQueue = Volley.newRequestQueue(this)
//        var jar = JsonArrayRequest(Request.Method.GET, urlToArray, null, Response.Listener<JSONArray> { response ->
//
//            if(result.isNullOrEmpty()){
//                textViewShowName.text = "Null response"
//                Log.d("showResult", "$result")
//
//            }
//            else{
//                for(x in 0..response.length()-1){
//                    result+= (response.getJSONObject(x).getString("name") + "-"
//                            + response.getJSONObject(x).getString("description")+ "\n"
//                            )
//                    textViewShowName.text = result
//                    Log.d("showResult", "$result")
//                }
//            }
//
//
//
//
//        }, Response.ErrorListener { error ->
//
//            textViewShowSalary.text = error.message
//            Log.e("errorCode" , "${error.message}")
//        })

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            urlToArray,
            null,
            Response.Listener { response ->
                // Do something with response
                //mTextView.setText(response.toString());

                // Process the JSON
                try {
                    // Loop through the array elements
                    for (i in 0 until response.length()) {
                        // Get current json object
                        val student = response.getJSONObject(i)

                        // Get the current student (json object) data
                        val firstName = student.getString("firstname")
                        val lastName = student.getString("lastname")
                        val age = student.getString("age")

                        // Display the formatted json data in text view
                        binding.textViewShowName.append("$firstName $lastName\nAge : $age")
                        binding.textViewShowName.append("\n\n")
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error->

                textViewShowSalary.text = error.message
//            Log.e("errorCode" , "${error.message}")
            }
        )
        rq.add(jsonArrayRequest)

    }

    private fun fetchfromServer(url : String) {

        val requestQueue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(
            Request.Method.GET, url,null,
            Response.Listener{
                    response ->
                if (response == null) {
                    Toast.makeText(
                        applicationContext,
                        "Couldn't fetch the menu! Pleas try again.",
                        Toast.LENGTH_LONG
                    ).show()

                }
               // for (x in 0..response.length()-1){
                    recipes = Gson().fromJson(
                        response.toString(),
                      object:  TypeToken<List<Repo?>?>() {}.getType())
               // }

                // adding data to cart list
              //  arrayList!!.clear()
//                recipes?.let { arrayList!!.addAll(it) }
//                arrayList.add(recipes)


                saveTask(recipes, Application())

            }, Response.ErrorListener {
                    error ->
                // error in getting json
                Log.e("TAG", "Error: " + error.message)
                Toast.makeText(
                    applicationContext,
                    "Error: " + error.message,
                    Toast.LENGTH_SHORT
                ).show()

            })

        request.setShouldCache(false)
        requestQueue.add(request)
    }

  private fun saveTask(recipes : List<Repo>? , context : Application) {
            //creating a task
            for (i in recipes!!.indices) {
                val recipe = Recipe()
                recipe.name = recipes[i]!!.toString()
                recipe.description = recipes!![i].toString()
               // recipe.price = recipes!![i].toString().toDouble()
                recipe.thumbnail = recipes!![i].toString()
                recipe.chef = recipes!![i].toString()
                recipe.timestamp = recipes!![i].toString()
                //database.appDatabase.recipeDao()?.insert(recipe)
                DatabaseClient(context).appDatabase.recipeDao()?.insert(recipe = recipes)

            }


           }



}
