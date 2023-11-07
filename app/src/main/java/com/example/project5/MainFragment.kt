package com.example.project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val sourceLanguageSelect = view.findViewById<RadioGroup>(R.id.sourceLanguageSelect)
        val translationSelect = view.findViewById<RadioGroup>(R.id.translationSelect)
        val translationTxt = view.findViewById<TextView>(R.id.translationTxt)

        var sourceLanguageButton = sourceLanguageSelect.checkedRadioButtonId
        var translationButton = translationSelect.checkedRadioButtonId

        var boolEnglishSpanish = false
        var boolEnglishGerman = false

        val optionsEnglishSpanish = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        val englishSpanishTranslator = Translation.getClient(optionsEnglishSpanish)

        val optionsEnglishGerman = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build()
        val englishGermanTranslator = Translation.getClient(optionsEnglishGerman)

        val downloadConditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        englishSpanishTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                boolEnglishSpanish = true
            }
            .addOnFailureListener {
                boolEnglishSpanish = false
            }

        englishGermanTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                boolEnglishGerman = true
            }
            .addOnFailureListener {
                boolEnglishGerman = false
            }

            viewModel.userText.observe(viewLifecycleOwner, Observer {
                println("Observed")
                val inputText = it
                translationTxt.text = inputText
            })

        return view
    }
}