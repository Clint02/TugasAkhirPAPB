package com.example.tugasakhirpapb

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
    }
}