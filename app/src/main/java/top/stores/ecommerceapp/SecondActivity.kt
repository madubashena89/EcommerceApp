package top.stores.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        textView2.text = "Welcome " + intent.getStringExtra("name")

        val url = "http://api.plos.org/search?q=title:%22Drosophila%22%20and%20body:%22RNA%22&fl=id,abstract&wt=json&indent=on"
        val requestQue : RequestQueue = Volley.newRequestQueue(this)
        var stringRequrst = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            textViewShowUrlFromWeb.text = response

        }, Response.ErrorListener {  error ->
            textViewShowUrlFromWeb.text = error.message
        })
         requestQue.add(stringRequrst)


        buttonSearchEmployees.setOnClickListener{
            var intentSendThirdActivity = Intent(this, ThirdActivity::class.java)
            startActivity(intentSendThirdActivity)
        }


    }



}
