package com.example.tugasakhirpapb

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvLogin: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.signup)
        btnRegister = findViewById(R.id.btn_register)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        tvLogin = findViewById(R.id.tv_login)
        btnRegister.setOnClickListener {
            val username = etEmail.text.toString()
            val password = etPassword.text.toString()

            //validasi email
            if (username.isEmpty()) {
                etEmail.error = "Email cannot be empty"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            //validasi email pattern
            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                etEmail.error = "Invalid email"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if (password.isEmpty()) {
                etPassword.error = "Password cannot be empty"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            //validasi panjang password
            if (password.length < 8) {
                etPassword.error = "Minimum password length is 8 characters"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            register(username, password)
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun register(username: String, password: String) {
        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}