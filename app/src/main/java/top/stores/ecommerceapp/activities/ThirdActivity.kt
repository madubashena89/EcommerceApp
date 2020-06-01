package top.stores.ecommerceapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*
import org.json.JSONArray
import org.json.JSONException
import top.stores.ecommerceapp.R
import top.stores.ecommerceapp.RoomDB.EmployeeEntity
import top.stores.ecommerceapp.databinding.ActivityThirdBinding
import top.stores.ecommerceapp.model.Employee
import top.stores.ecommerceapp.repository.EmployeeRepository
import top.stores.ecommerceapp.viewModels.EmployeeViewModel


class ThirdActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel

    var recipes: List<EmployeeRepository>? = null
    var urlToArray = "https://api.androidhive.info/json/shimmer/menu.php"
    private var arrayList: ArrayList<EmployeeRepository>? = null
    private lateinit var  binding : ActivityThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
       binding = DataBindingUtil.setContentView(this,
           R.layout.activity_third
       )
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
       fetchJsonEmployeeObject()
       showEmployeesFromViewModel()
       // fetchJsonEmployeeObject()
    }


    fun showEmployeesFromViewModel(){
        binding.textViewShowName.apply {
            viewModel.getAllEMployees().toString()
        }

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



//  private fun saveTask(recipes : List<EmployeeRepository>?, context : Application) {
//            //creating a task
//            for (i in recipes!!.indices) {
//                val recipe = Recipe()
//                recipe.name = recipes[i]!!.toString()
//                recipe.description = recipes!![i].toString()
//               // recipe.price = recipes!![i].toString().toDouble()
//                recipe.thumbnail = recipes!![i].toString()
//                recipe.chef = recipes!![i].toString()
//                recipe.timestamp = recipes!![i].toString()
//                //database.appDatabase.recipeDao()?.insert(recipe)
//                DatabaseClient(context).appDatabase.recipeDao()?.insert(recipe = recipes)
//
//            }
//
//
//           }

    private fun makeJsonEmployeeObject(){

        val gson = Gson()
        val employee : Employee = Employee("Suresh", 34, "Gen")
        val json = gson.toJson(employee)

    }

    private fun fetchJsonEmployeeObject(){

        var urlToArray = "https://pastebin.com/raw/Em972E5s"
        var list = ArrayList<String>()
        var result:String = ""

        var rq : RequestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            urlToArray,
            null,
            Response.Listener { response ->
                fromJsonEmployeeEntity(response)
            },
            Response.ErrorListener { error->

                textViewShowSalary.text = error.message
//            Log.e("errorCode" , "${error.message}")
            }
        )
        rq.add(jsonArrayRequest)

    }

    private fun fromJsonEmployee(response: JSONArray){
        val gson = Gson()
        val json : String = response.toString()
        val employee: Array<Employee> = gson.fromJson(json, Array<Employee>::class.java)
        Log.d("employee", "$employee")

    }


    private fun fromJsonEmployeeEntity(response: JSONArray){
        val json : String = response.toString()

        val gson: Gson = GsonBuilder()
                          .create()

        val employee : Array<EmployeeEntity> = gson.fromJson(json, Array<EmployeeEntity>::class.java)
        //Log.d("employee", "$employee")
       // Log.d("gson", "$gson")
        Log.d("json", "$json")


    }






}
