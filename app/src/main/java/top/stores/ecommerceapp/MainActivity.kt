package top.stores.ecommerceapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonSendSecondAct.setOnClickListener{

            var intentTo = Intent(this, SecondActivity::class.java)
            intentTo.putExtra("name", txtView1.text.toString())
            startActivity(intentTo)




        }

        buttonSendSharePref.setOnClickListener{
            var sp = getSharedPreferences("my_name", Context.MODE_PRIVATE)
            var editor = sp.edit()
            editor.putString("name", txtView1.text.toString())
            editor.commit()
        }




        buttonGetNameSharedPred.setOnClickListener{
            var sp = getSharedPreferences("my_name", Context.MODE_PRIVATE)
            textViewShowName.text = sp.getString("name", "No Name")

        }




    }
}
