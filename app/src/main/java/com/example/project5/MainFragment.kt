package com.example.project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions


class MainFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        println("Running main")

        val sourceLanguageSelect = view.findViewById<RadioGroup>(R.id.sourceLanguageSelect)
        val translationSelect = view.findViewById<RadioGroup>(R.id.translationSelect)
        val translationTxt = view.findViewById<TextView>(R.id.translationTxt)


        sourceLanguageSelect.setOnCheckedChangeListener{ group, checkedId ->
            println("Checked")
            if(checkedId == R.id.sourceEnglish){
                viewModel.sourceLanguage = "English"
                println("Source " + viewModel.sourceLanguage)
            }
            else if(checkedId == R.id.sourceSpanish){
                viewModel.sourceLanguage = "Spanish"
                println("Source " + viewModel.sourceLanguage)
            }
            else if(checkedId == R.id.sourceGerman){
                viewModel.sourceLanguage = "German"
                println("Source " + viewModel.sourceLanguage)
            }
        }

        translationSelect.setOnCheckedChangeListener{ group, checkedId ->
            println("Checked")
            if(checkedId == R.id.translationSpanish){
                viewModel.targetLanguage = "Spanish"
                println("Target " + viewModel.targetLanguage)
            }
            else if(checkedId == R.id.translationGerman){
                viewModel.targetLanguage = "German"
                println("Target " + viewModel.targetLanguage)
            }
            else if(checkedId == R.id.translationEnglish){
                viewModel.targetLanguage = "English"
                println("Target " + viewModel.targetLanguage)
            }
        }



        viewModel.translation.observeForever {
            translationTxt.text = viewModel.translation.toString()
        }

        return view
    }
}