package com.example.freelancer

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freelancer.models.Offre
import com.example.freelancer.models.User
import com.example.freelancer.offrelist.OffreAdapter
import com.example.freelancer.offrelist.OffreAdapterAccept
import com.example.freelancer.utils.ApiInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyOffre : AppCompatActivity() {
    lateinit var recylcerChampion: RecyclerView
    lateinit var recylcerChampionAdapter: OffreAdapterAccept
    var champList : MutableList<Offre> = ArrayList()
    lateinit var nowuser: User
    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_offre)
        //toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        val gson = Gson()
        val  us =  mSharedPref.getString(myuser, "")

        nowuser = gson.fromJson(us,User::class.java)

        toolbar.setNavigationOnClickListener {


            finish()
        }
        recylcerChampion = findViewById(R.id.recyclerChampion)



        doLogin()


        recylcerChampionAdapter = OffreAdapterAccept(champList)
        recylcerChampion.adapter = recylcerChampionAdapter
        recylcerChampion.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL ,false)
    }

    private fun doLogin(){

        val apiInterface = ApiInterface.create()



        apiInterface.alloffre().enqueue(object : Callback<MutableList<Offre>> {

            override fun onResponse(call: Call<MutableList<Offre>>, response: Response<MutableList<Offre>>) {

                val user = response.body()

                if (user != null) {
                    for(a in user){
                        if(a.UserId==nowuser.id){
                            champList.add(a)
                        }
                    }

                    recylcerChampionAdapter = OffreAdapterAccept(ArrayList(champList.asReversed()))
                    recylcerChampion.adapter = recylcerChampionAdapter

                }



            }

            override fun onFailure(call: Call<MutableList<Offre>>, t: Throwable) {

            }

        })


    }
}