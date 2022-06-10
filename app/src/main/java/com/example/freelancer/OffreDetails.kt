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
import com.example.freelancer.utils.ApiInterface
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OffreDetails : AppCompatActivity() {
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
    lateinit var nowuser: User

    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offre_details)
        //toolbar
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
            amainIntent = Intent(this, MainActivity::class.java)


            doLogin()


        }


    }
    private fun doLogin(){
        val gson = Gson()
        val  us =  mSharedPref.getString(myuser, "")

        nowuser = gson.fromJson(us, User::class.java)
        val apiInterface = ApiInterface.create()


        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
//put something inside the map, could be null
//put something inside the map, could be null
        jsonParams["description"] = description
        jsonParams["UserIdAccept"] = nowuser.id
        jsonParams["subject"] = subject
        jsonParams["Price"] = Price
        jsonParams["Time"] = Time
        jsonParams["Status"] = "true"




        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface.updateoffre(body,idOffre).enqueue(object : Callback<Offre> {

            override fun onResponse(call: Call<Offre>, response: Response<Offre>) {

                val user = response.body()

                if (user != null){
                    Toast.makeText(this@OffreDetails, "Offre Accepted", Toast.LENGTH_SHORT).show()
                    Log.d("user",user.toString())


                    startActivity(amainIntent)
                    finish()
                }else{
                    Toast.makeText(this@OffreDetails, "User can not Accept", Toast.LENGTH_SHORT).show()
                }


                window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }

            override fun onFailure(call: Call<Offre>, t: Throwable) {
                Toast.makeText(this@OffreDetails, t.message, Toast.LENGTH_SHORT).show()


                window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }

        })


    }
}