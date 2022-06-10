package com.example.freelancer

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
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

class AddOfffre : AppCompatActivity() {
    private lateinit var txtEditSub: EditText
    private lateinit var txtEditPrice: EditText
    private lateinit var txtEditDescri: EditText
    private lateinit var txtEditTime: EditText
    lateinit var TextSubject: TextView
    lateinit var btnListReponse: Button
    lateinit var nowuser: User

    private lateinit var mSharedPref: SharedPreferences
    lateinit var amainIntent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_offfre)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




        toolbar.setNavigationOnClickListener {


            finish()
        }

        amainIntent = Intent(this, MainActivity::class.java)
        txtEditSub = findViewById(R.id.EdittextSubject)
        txtEditPrice = findViewById(R.id.EdittextPrice)
        txtEditDescri = findViewById(R.id.Edittext)
        txtEditTime = findViewById(R.id.EdittextTime)

        TextSubject = findViewById(R.id.QuestionSubject)
        btnListReponse= findViewById(R.id.btnListReponse)


        btnListReponse!!.setOnClickListener {

            if (txtEditSub?.text!!.isEmpty()) {
                Toast.makeText(this@AddOfffre, "Subject must not be empty", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            if (txtEditDescri?.text!!.isEmpty()) {
                Toast.makeText(this@AddOfffre, "Description must not be empty", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            if (txtEditPrice?.text!!.isEmpty()) {
                Toast.makeText(this@AddOfffre, "Price must not be empty", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            if (txtEditTime?.text!!.isEmpty()) {
                Toast.makeText(this@AddOfffre, "Time must not be empty", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            doLogin()
        }
    }
    private fun doLogin(){
        val gson = Gson()
        val  us =  mSharedPref.getString(myuser, "")

        nowuser = gson.fromJson(us,User::class.java)
        val apiInterface = ApiInterface.create()


        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
//put something inside the map, could be null
//put something inside the map, could be null
        jsonParams["description"] = txtEditDescri.text.toString()
        jsonParams["UserId"] = nowuser.id
        jsonParams["subject"] = txtEditSub.text.toString()
        jsonParams["Price"] = txtEditPrice.text.toString()+" DT"
        jsonParams["Time"] = txtEditTime.text.toString()+" Days"
        jsonParams["Status"] = "false"
        jsonParams["imageClient"] = nowuser.imageUrl


        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface.createoffre(body).enqueue(object : Callback<Offre> {

            override fun onResponse(call: Call<Offre>, response: Response<Offre>) {

                val user = response.body()

                if (user != null){
                    Toast.makeText(this@AddOfffre, "Offre Aded", Toast.LENGTH_SHORT).show()


                    startActivity(amainIntent)
                    finish()
                }else{
                    Toast.makeText(this@AddOfffre, "can not Add Offre", Toast.LENGTH_SHORT).show()
                }


                window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }

            override fun onFailure(call: Call<Offre>, t: Throwable) {
                Toast.makeText(this@AddOfffre, t.message, Toast.LENGTH_SHORT).show()


                window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }

        })


    }
}