//package top.stores.ecommerceapp
//
//import android.R
//import android.net.ConnectivityManager
//import android.os.AsyncTask
//import android.os.Bundle
//import android.widget.ProgressBar
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
//import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
//import com.android.volley.VolleyError
//import com.android.volley.toolbox.JsonArrayRequest
//import com.android.volley.toolbox.Volley
//import org.json.JSONArray
//import top.stores.ecommerceapp.RoomDB.DatabaseClient
//import top.stores.ecommerceapp.RoomDB.Recipe
//
//
//class MainActivity : AppCompatActivity() {
//    var recipes: List<Repo>? = null
//    private var recyclerview: RecyclerView? = null
//    private var arrayList: ArrayList<Repo>? = null
//    private var adapter: CustomRecyclerview? = null
//    private var pb: ProgressBar? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        pb = findViewById(R.id.pb)
//        pb.setVisibility(View.GONE)
//        recyclerview = findViewById(R.id.recyclerview)
//        arrayList = ArrayList()
//        adapter = CustomRecyclerview(this, arrayList)
//        val mLayoutManager: LayoutManager = LinearLayoutManager(applicationContext)
//        recyclerview.setLayoutManager(mLayoutManager)
//        recyclerview.setItemAnimator(DefaultItemAnimator())
//        recyclerview.setNestedScrollingEnabled(false)
//        recyclerview.setAdapter(adapter)
//        val cm = MyApplication.getInstance().getApplicationContext()
//            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = cm.activeNetworkInfo
//        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting && arrayList != null) {
//            fetchfromServer()
//        } else {
//            fetchfromRoom()
//        }
//    }
//
//    private fun fetchfromRoom() {
//        val thread = Thread(Runnable {
//            val recipeList: List<Recipe> =
//                DatabaseClient.getInstance(this@MainActivity).getAppDatabase().recipeDao()
//                    .getAll()
//            arrayList!!.clear()
//            for ((id, name, description, price, thumbnail, chef, timestamp) in recipeList) {
//                val repo = Repo(
//                    id, name,
//                    description,
//                    price,
//                    thumbnail,
//                    chef,
//                    timestamp
//                )
//                arrayList!!.add(repo)
//            }
//            // refreshing recycler view
//            runOnUiThread { adapter.notifyDataSetChanged() }
//        })
//        thread.start()
//    }
//
//    private fun fetchfromServer() {
//        pb!!.visibility = View.VISIBLE
//        val request = JsonArrayRequest(
//            FETCHURL,
//            object : Listener<JSONArray?>() {
//                fun onResponse(response: JSONArray?) {
//                    if (response == null) {
//                        pb!!.visibility = View.GONE
//                        Toast.makeText(
//                            applicationContext,
//                            "Couldn't fetch the menu! Pleas try again.",
//                            Toast.LENGTH_LONG
//                        ).show()
//                        return
//                    }
//                    recipes = Gson().fromJson(
//                        response.toString(),
//                        object : TypeToken<List<Repo?>?>() {}.getType()
//                    )
//
//                    // adding data to cart list
//                    arrayList!!.clear()
//                    arrayList!!.addAll(recipes!!)
//
//
//                    // refreshing recycler view
//                    adapter.notifyDataSetChanged()
//                    pb!!.visibility = View.GONE
//                    saveTask()
//                }
//            }, object : ErrorListener() {
//                fun onErrorResponse(error: VolleyError) {
//                    // error in getting json
//                    pb!!.visibility = View.GONE
//                    Log.e("TAG", "Error: " + error.message)
//                    Toast.makeText(
//                        applicationContext,
//                        "Error: " + error.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
//        val requestQueue = Volley.newRequestQueue(this)
//        request.setShouldCache(false)
//        requestQueue.add(request)
//    }
//
//    private fun saveTask() {
//        class SaveTask :
//            AsyncTask<Void?, Void?, Void?>() {
//            protected override fun doInBackground(vararg voids: Void): Void? {
//
//                //creating a task
//                for (i in recipes!!.indices) {
//                    val recipe = Recipe()
//                    recipe.name = recipes!![i].getName()
//                    recipe.description = recipes!![i].getDescription()
//                    recipe.price = recipes!![i].getPrice()
//                    recipe.thumbnail = recipes!![i].getThumbnail()
//                    recipe.chef = recipes!![i].getChef()
//                    recipe.timestamp = recipes!![i].getTimestamp()
//                    DatabaseClient.getInstance(applicationContext).getAppDatabase().recipeDao()
//                        .insert(recipe)
//                }
//                return null
//            }
//
//            override fun onPostExecute(aVoid: Void?) {
//                super.onPostExecute(aVoid)
//                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        val st = SaveTask()
//        st.execute()
//    }
//
//    companion object {
//        private const val FETCHURL = "https://api.androidhive.info/json/shimmer/menu.php"
//    }
//}
