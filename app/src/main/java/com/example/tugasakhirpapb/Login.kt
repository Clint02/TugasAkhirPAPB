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

class Login : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvSignUp: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.login)
        btnLogin = findViewById(R.id.btn_login)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        tvSignUp = findViewById(R.id.tv_signup)
        btnLogin.setOnClickListener {
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

            login(username, password)
        }

        tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun login(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                callActivity()
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun callActivity() {
        val username = findViewById<EditText>(R.id.et_email).text.toString()
        val intent = Intent(this, MainActivity::class.java).also {
            it.putExtra("username", username)
        }
        startActivity(intent)
    }
}