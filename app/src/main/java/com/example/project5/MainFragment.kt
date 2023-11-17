package com.example.project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class MainFragment : Fragment() {
    lateinit var viewModel: ViewModel
    //private var _binding: FragmentMainBinding? = null
    //private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //_binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("Running main")

        val sourceLanguageSelect = view.findViewById<RadioGroup>(R.id.sourceLanguageSelect)
        val translationSelect = view.findViewById<RadioGroup>(R.id.translationSelect)
        val translationTxt = view.findViewById<TextView>(R.id.translationTxt)


        sourceLanguageSelect.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.sourceEnglish){
//                viewModel.sourceLanguage = "English"

                println("Source " + viewModel.sourceLanguage)
                viewModel.setSourceLang("English")
            }
            else if(checkedId == R.id.sourceSpanish){
//                viewModel.sourceLanguage = "Spanish"
                println("Source " + viewModel.sourceLanguage)
            }
            else if(checkedId == R.id.sourceGerman){
//                viewModel.sourceLanguage = "German"
                println("Source " + viewModel.sourceLanguage)
            }
        }

        translationSelect.setOnCheckedChangeListener{ group, checkedId ->
            if(checkedId == R.id.translationSpanish){
                viewModel.targetLanguage = "Spanish"
                println("Target " + viewModel.targetLanguage)
            }
            else if(checkedId == R.id.translationGerman){
                viewModel.targetLanguage ="German"
                println("Target " + viewModel.targetLanguage)
            }
            else if(checkedId == R.id.translationEnglish){
                viewModel.targetLanguage = "English"
                println("Target " + viewModel.targetLanguage)
            }
            println("Target set ${viewModel.targetLanguage}")
        }


        viewModel.translation.observeForever {
            println("Observed")
            translationTxt.text = viewModel.translation.toString()
        }
    }
}