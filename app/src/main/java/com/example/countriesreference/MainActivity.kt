package com.example.countriesreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                val counties: List<Country> = restCountriesApi.getCountryByName(countryName)
                val country: Country = counties[0]

                binding.countryNameTextView.text = country.name
                binding.capitalNameTextView.text = country.capital
                binding.populationNumberTextView.text = country.population.toString()
                binding.areaNumberTextView.text = country.area.toString()
                binding.languagesNamesTextView.text = country.languages.toString()

            }
        }
    }
}