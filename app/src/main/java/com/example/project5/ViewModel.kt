package com.example.project5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

public class ViewModel : ViewModel() {
    val userText: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }


}