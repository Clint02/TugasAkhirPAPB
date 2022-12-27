package com.example.tugasakhirpapb

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class AddRs : AppCompatActivity() {

    private lateinit var database: FirebaseFirestore

    private lateinit var btnSubmit: Button
    private lateinit var etNama: EditText
    private lateinit var etJam: EditText
    private lateinit var etAlamat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_rs)
        supportActionBar?.hide()

        database = FirebaseFirestore.getInstance()

        btnSubmit = findViewById(R.id.btn_submit)
        etNama = findViewById(R.id.et_nama)
        etJam = findViewById(R.id.et_jam)
        etAlamat = findViewById(R.id.et_alamat)

        btnSubmit.setOnClickListener {
            val add = HashMap<String, Any>()

            add["nama"] = etNama.text.toString()
            add["jam"] = etJam.text.toString()
            add["alamat"] = etAlamat.text.toString()

            database.collection("RS").add(add).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Adding data successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this," Data not added ",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}