package com.example.freelancer.offrelist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freelancer.OffreDetails
import com.example.freelancer.R
import com.example.freelancer.models.Offre
import com.example.freelancer.utils.ApiInterface

class OffreAdapter (val QuestionList: MutableList<Offre>) : RecyclerView.Adapter<OffreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemquestion, parent, false)

        return OffreViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffreViewHolder, position: Int) {


        val description = QuestionList[position].description
        val subject = QuestionList[position].subject

        val idClient = QuestionList[position].UserId
        val imaClient = QuestionList[position].imageClient
        val idOffre = QuestionList[position].id
        val price = QuestionList[position].Price
        val time = QuestionList[position].Time
        var imagee= QuestionList[position].imageClient
        holder.QuestionDescription.text = description
        holder.QuestionSubject.text = subject

        if( imaClient!=null){
            imagee = "uploads/"+ imaClient.subSequence(8,imaClient.length)
            Log.d("image",imagee)
        }


        Glide.with(holder.QuestionPic).load(ApiInterface.BASE_URL + imagee).placeholder(R.drawable.ic_account).circleCrop()
            .error(R.drawable.ic_baseline_account_circle_24).into(holder.QuestionPic)




        holder.itemView.setOnClickListener{ v ->

            val intent = Intent(v.context, OffreDetails::class.java)
            intent.putExtra("description",description);
            intent.putExtra("subject",subject);
            intent.putExtra("idClient",idClient);
            intent.putExtra("idOffre",idOffre);
            intent.putExtra("Price",price);
            intent.putExtra("Time",time);
            v.context.startActivity(intent)

        }




    }

    override fun getItemCount() = QuestionList.size

}