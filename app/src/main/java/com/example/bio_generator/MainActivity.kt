package com.example.bio_generator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils.split
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bio_generator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var errorMessage: String
    private lateinit var paragraph: String
    private lateinit var binding: ActivityMainBinding
    var isFilled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitBtn.setOnClickListener { generateParagraf() }
        binding.resetBtn.setOnClickListener { reset() }
        binding.copyBtn.setOnClickListener { copyToClipboard() }
    }

    fun String.capitalizeWords() = split(' ').joinToString(" ", transform = String::capitalize)

    private fun generateParagraf() {
        val name = binding.name.text.toString().capitalizeWords()
        val placeOfBirth = binding.placeOfBirth.text.toString().capitalizeWords()
        val dateOfBirth = binding.dateOfBirth.text.toString()
        val hobby = binding.hobby.text.toString()
        errorMessage == "Lengkapi data terlebih dahulu!"
        paragraph =
            "Namaku $name. Aku lahir di $placeOfBirth, pada tanggal $dateOfBirth. So, Aku anak $placeOfBirth. Hobiku $hobby."
        if (name == "" || placeOfBirth == "" || dateOfBirth == "" || hobby == "") {
            binding.paragraph.setText(errorMessage)
            return
        }
        isFilled = true
        binding.paragraph.setText(paragraph)
    }

    private fun reset() {
        binding.name.text.clear()
        binding.placeOfBirth.text.clear()
        binding.dateOfBirth.text.clear()
        binding.hobby.text.clear()
        isFilled = false
    }

    private fun copyToClipboard() {
        if (isFilled) {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("paragraf", binding.paragraph.text.toString())
            clipboard.setPrimaryClip(clip)
        } else {
            Toast.makeText(applicationContext, "Lengkapi data terlebih dahulu!", Toast.LENGTH_SHORT)
                .show()
        }
    }

}