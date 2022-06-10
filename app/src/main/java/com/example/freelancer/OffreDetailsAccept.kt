package com.example.freelancer

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.freelancer.models.Offre
import com.example.freelancer.models.User
import com.example.freelancer.offrelist.OffreAdapter
import com.example.freelancer.utils.ApiInterface
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OffreDetailsAccept : AppCompatActivity() {
    private lateinit var txtEditSub: EditText
    private lateinit var txtEditPrice: EditText
    private lateinit var txtEditDescri: EditText
    private lateinit var txtEditTime: EditText
    lateinit var TextSubject: TextView
    lateinit var btnListReponse: Button
    lateinit var description : String

    lateinit var subject : String
    lateinit var idClient : String

    lateinit var idOffre : String
    lateinit var Price : String
    lateinit var amainIntent : Intent
    lateinit var Time : String
    lateinit var idAccept : String

    lateinit var Status : String
    lateinit var nowuser: User
    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offre_details_accept)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




        toolbar.setNavigationOnClickListener {


            finish()
        }

        val myIntent = intent
        description = myIntent.getStringExtra("description").toString()

        subject = myIntent.getStringExtra("subject").toString()
        idClient = myIntent.getStringExtra("idClient").toString()

        idOffre = myIntent.getStringExtra("idOffre").toString()
        Price = myIntent.getStringExtra("Price").toString()

        Time = myIntent.getStringExtra("Time").toString()
        Status = myIntent.getStringExtra("Status").toString()
        txtEditSub = findViewById(R.id.EdittextSubject)
        txtEditPrice = findViewById(R.id.EdittextPrice)
        txtEditDescri = findViewById(R.id.Edittext)
        txtEditTime = findViewById(R.id.EdittextTime)

        TextSubject = findViewById(R.id.QuestionSubject)
        btnListReponse= findViewById(R.id.btnListReponse)
        txtEditSub.setText(subject)
        txtEditPrice.setText(Price)
        txtEditDescri.setText(description)
        txtEditTime.setText(Time)
        TextSubject.isEnabled =false
        btnListReponse!!.setOnClickListener {

if(Status == "false"){
    Toast.makeText(this@OffreDetailsAccept, "Offre Didn't Accepted Yet !!", Toast.LENGTH_SHORT).show()
}else{
    amainIntent = Intent(this, acceptefdetails::class.java)
    idAccept= myIntent.getStringExtra("idAccept").toString()
    Log.d("idAccept",idAccept)
    doLogin()

}




        }


    }
    private fun doLogin(){

        val apiInterface = ApiInterface.create()



        apiInterface.getByid(idAccept).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                val user = response.body()

                if (user != null) {Log.d("user",user.toString())

                    amainIntent.putExtra("nom",user.nom);
                    amainIntent.putExtra("phone",user.phone);
                    amainIntent.putExtra("imageUrl",user.imageUrl);
                    amainIntent.putExtra("email",user.email);


                    startActivity(amainIntent)



                }



            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })


    }
}