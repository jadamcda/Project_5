package com.example.project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider


class InputFragment : Fragment() {
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        println("Running input")

        val inputEditText = view.findViewById<EditText>(R.id.inputEditText)

        inputEditText.doAfterTextChanged {
            viewModel.userText.value = inputEditText.text.toString()
        }

        return view
    }
}