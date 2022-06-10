package com.example.freelancer

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.freelancer.models.User
import com.example.freelancer.utils.ApiInterface
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.Gson

class acceptefdetails : AppCompatActivity() {
    private lateinit var txtFullName: TextView
    private lateinit var txtName: TextView
    private lateinit var txtemail: TextView
    private lateinit var txtPhone: TextView

    private lateinit var imageme: ShapeableImageView

    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acceptefdetails)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




        toolbar.setNavigationOnClickListener {


            finish()
        }

        txtFullName = findViewById(R.id.idfullname)
        txtFullName.isEnabled = false
        mSharedPref =  getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        txtName =findViewById(R.id.idAddressacc)
        txtName.isEnabled = false
        txtPhone = findViewById(R.id.idphone)
        txtPhone.isEnabled = false
        txtemail = findViewById(R.id.idEmail)
        txtemail.isEnabled = false
        imageme = findViewById(R.id.idUrlImg)

        val myIntent = intent
        val nameint = myIntent.getStringExtra("nom")

        val phoneint = myIntent.getStringExtra("phone")
        val emailint = myIntent.getStringExtra("email")
        val imageint = myIntent.getStringExtra("imageUrl")
        var imagee =""
        if( imageint!=null){
            imagee = "uploads/"+ imageint.subSequence(8,imageint.length)

        }
        Glide.with(imageme).load(ApiInterface.BASE_URL + imagee).placeholder(R.drawable.ic_account).circleCrop()
            .error(R.drawable.ic_baseline_account_circle_24).into(imageme)


        txtFullName.text = nameint
        txtName.text = nameint
        txtPhone.text = phoneint
        txtemail.text = emailint


    }
}