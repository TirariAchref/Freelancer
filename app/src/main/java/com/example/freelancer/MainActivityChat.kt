package com.example.freelancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.freelancer.models.ChatUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.name

class MainActivityChat : Fragment() {

    private lateinit var navController: NavController
    private val client = ChatClient.instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_main_chat, container, false)

        navController = Navigation.findNavController(rootView.findViewById(R.id.navHostFragment))

        if (navController.currentDestination?.label.toString().contains("login")) {
            val currentUser = client.getCurrentUser()
            if (currentUser != null) {
                val user = ChatUser(currentUser.name, currentUser.id)
                val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
                navController.navigate(action)
            }
        }
        return rootView

    }



}