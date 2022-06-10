package com.example.freelancer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freelancer.models.Offre
import com.example.freelancer.offrelist.OffreAdapter
import com.example.freelancer.utils.ApiInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OffreFragment : Fragment() {

    lateinit var recylcerChampion: RecyclerView
    lateinit var recylcerChampionAdapter: OffreAdapter
    var champList : MutableList<Offre> = ArrayList()

    lateinit var btnadd: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_offre, container, false)

        btnadd = rootView.findViewById(R.id.fab)
        btnadd.setOnClickListener{
            val intent = Intent(activity, AddOfffre::class.java)
            startActivity(intent)

        }

        recylcerChampion = rootView.findViewById(R.id.recyclerChampion)



        doLogin()


        recylcerChampionAdapter = OffreAdapter(champList)
        recylcerChampion.adapter = recylcerChampionAdapter
        recylcerChampion.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)


        return rootView

    }

    private fun doLogin(){

        val apiInterface = ApiInterface.create()



        apiInterface.alloffre().enqueue(object : Callback<MutableList<Offre>> {

            override fun onResponse(call: Call<MutableList<Offre>>, response: Response<MutableList<Offre>>) {

                val user = response.body()

                if (user != null) {
                    for(a in user){
                        if(a.Status == "false"){
                            champList.add(a)
                        }
                    }


                    recylcerChampionAdapter = OffreAdapter(ArrayList(champList.asReversed()))
                    recylcerChampion.adapter = recylcerChampionAdapter

                }



            }

            override fun onFailure(call: Call<MutableList<Offre>>, t: Throwable) {

            }

        })


    }

}