package com.example.tugasakhirpapb.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tugasakhirpapb.AddRs
import com.example.tugasakhirpapb.Login
import com.example.tugasakhirpapb.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Profile : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var email : EditText
    private lateinit var logout : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        email = binding.email
        logout = binding.btnLogout

        val user = Firebase.auth.currentUser
        user?.let {
            val emailf = user.email
            email.setText(emailf)
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, Login::class.java)
            Toast.makeText(requireContext(), "Logout Successful", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        return binding.root
    }
}