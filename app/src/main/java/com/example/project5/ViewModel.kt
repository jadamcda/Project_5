package com.example.project5

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

public class ViewModel : ViewModel() {
    var sourceLanguage = "English"
    var targetLanguage = "Spanish"

    var boolEnglishSpanish = false
    var boolEnglishGerman = false


    val userText: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val translation: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    init {
        println("Running viewModel")

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

        userText.observeForever {
            input ->
            println("Intput: " + input)
            englishGermanTranslator.translate(input)
                .addOnSuccessListener { translatedText ->
                    println("Translation: " + translatedText)
                    translation.value = translatedText
                }
        }
    }

}