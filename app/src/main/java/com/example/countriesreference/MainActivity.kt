package com.example.countriesreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.countriesreference.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val countryName: String = binding.countryNameEditText.text.toString()

            lifecycleScope.launch {
                try{
                    val counties: List<Country> = restCountriesApi.getCountryByName(countryName)

                val country: Country = counties[0]

                binding.countryNameTextView.text = country.name
                binding.capitalNameTextView.text = country.capital
                binding.populationNumberTextView.text = formantNumber(country.population)
                binding.areaNumberTextView.text = formantNumber(country.area)
                binding.languagesListTextView.text = langugesToString(country.languages)

                loadSvg(binding.imageView, country.flag)
                binding.resultLayout.visibility = View.VISIBLE
                binding.resultLayout.visibility = View.INVISIBLE
                }catch (e: Exception){
                    binding.statusTextView.text = "Country not found"
                    binding.statusImageFiew.setImageResource(R.drawable.ic_baseline_error_24)
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.resultLayout.visibility = View.VISIBLE

                }
            }
        }
    }
}