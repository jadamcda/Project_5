package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import com.example.project5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding
//    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
        setContentView(R.layout.activity_main)
    }
}