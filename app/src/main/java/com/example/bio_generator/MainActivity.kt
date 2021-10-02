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
        val nama = binding.nama.text.toString().capitalizeWords()
        val tempatLahir = binding.tempatLahir.text.toString().capitalizeWords()
        val tanggalLahir = binding.tanggalLahir.text.toString()
        val hobi = binding.hobi.text.toString()
        if (nama == "" || tempatLahir == "" || tanggalLahir == "" || hobi == "") {
            binding.paragraf.setText("Lengkapi data terlebih dahulu!")
            return
        }
        isFilled = true
        binding.paragraf.setText("Namaku $nama. Aku lahir di $tempatLahir, pada tanggal $tanggalLahir. So, Aku anak $tempatLahir. Hobiku $hobi.")
    }

    private fun reset() {
        binding.nama.text.clear()
        binding.tempatLahir.text.clear()
        binding.tanggalLahir.text.clear()
        binding.hobi.text.clear()
        isFilled = false
    }

    private fun copyToClipboard() {
        if (isFilled) {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("paragraf", binding.paragraf.text.toString())
            clipboard.setPrimaryClip(clip)
        } else {
            Toast.makeText(applicationContext, "Lengkapi data terlebih dahulu!", Toast.LENGTH_SHORT)
                .show()
        }
    }

}