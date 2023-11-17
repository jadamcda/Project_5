package com.example.project5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class ViewModel : ViewModel() {

    var targetLanguage: String = ""
//    var sourceLanguage: String = ""
    var boolEnglishSpanish = false
    var boolEnglishGerman = false
    var boolSpanishGerman = false
    var boolSpanishEnglish = false
    var boolGermanSpanish = false
    var boolGermanEnglish = false
    fun setSourceLang(language: String){
        sourceLanguage.value = language
    }
    val sourceLanguage: MutableLiveData<String>  by lazy{
        MutableLiveData<String>()
    }
    val userText: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val translation: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    init {

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

        val optionsSpanishGerman = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build()
        val spanishGermanTranslator = Translation.getClient(optionsSpanishGerman)

        val optionsSpanishEnglish = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val spanishEnglishTranslator = Translation.getClient(optionsSpanishEnglish)

        val optionsGermanSpanish = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.GERMAN)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        val germanSpanishTranslator = Translation.getClient(optionsGermanSpanish)

        val optionsGermanEnglish = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.GERMAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val germanEnglishTranslator = Translation.getClient(optionsGermanEnglish)

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

        spanishGermanTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                boolSpanishGerman = true
            }
            .addOnFailureListener {
                boolSpanishGerman = false
            }

        spanishEnglishTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                boolSpanishEnglish = true
            }
            .addOnFailureListener {
                boolSpanishEnglish = false
            }

        germanSpanishTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                boolGermanSpanish = true
            }
            .addOnFailureListener {
                boolGermanSpanish = false
            }

        germanEnglishTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                boolGermanEnglish = true
            }
            .addOnFailureListener {
                boolGermanEnglish= false
            }

        userText.observeForever {
            input ->
            println("Intput: " + input)
            println("Source language: " + sourceLanguage.value)
            println("Target language: " + targetLanguage)

            if(sourceLanguage.value == targetLanguage){
                translation.value = input
            }
            else if(sourceLanguage.value == "English" && targetLanguage == "German"){
                englishGermanTranslator.translate(input)
                    .addOnSuccessListener { translatedText ->
                        println("Translation: " + translatedText)
                        translation.value = translatedText
                    }
            }
            else if(sourceLanguage.value == "English" && targetLanguage == "Spanish"){
                englishSpanishTranslator.translate(input)
                    .addOnSuccessListener { translatedText ->
                        println("Translation: " + translatedText)
                        translation.value = translatedText
                    }
            }
            else if(sourceLanguage.value == "Spanish" && targetLanguage == "German"){
                spanishGermanTranslator.translate(input)
                    .addOnSuccessListener { translatedText ->
                        println("Translation: " + translatedText)
                        translation.value = translatedText
                    }
            }
            else if(sourceLanguage.value == "Spanish" && targetLanguage == "English"){
                spanishEnglishTranslator.translate(input)
                    .addOnSuccessListener { translatedText ->
                        println("Translation: " + translatedText)
                        translation.value = translatedText
                    }
            }
            else if(sourceLanguage.value == "German" && targetLanguage == "Spanish"){
                germanSpanishTranslator.translate(input)
                    .addOnSuccessListener { translatedText ->
                        println("Translation: " + translatedText)
                        translation.value = translatedText
                    }
            }
            else if(sourceLanguage.value == "German" && targetLanguage == "English"){
                germanEnglishTranslator.translate(input)
                    .addOnSuccessListener { translatedText ->
                        println("Translation: " + translatedText)
                        translation.value = translatedText
                    }
            }
        }
    }

}