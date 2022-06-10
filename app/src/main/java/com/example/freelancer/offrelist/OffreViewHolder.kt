package com.example.freelancer.offrelist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freelancer.R

class OffreViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val QuestionPic : ImageView
    val QuestionDescription : TextView
    val QuestionSubject: TextView = itemView.findViewById<TextView>(R.id.Subject)

    init {
        QuestionPic = itemView.findViewById<ImageView>(R.id.picc)
        QuestionDescription = itemView.findViewById<TextView>(R.id.Description)
    }

}