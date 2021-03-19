package com.example.projet3

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projet3.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val myIntent = intent
        val myBundle = myIntent.extras
        val p = myBundle?.getParcelable<Person>("personSelected")
        //Log.i("SaadR",p!!.nom)
        binding.nom3.text = p!!.nom
        binding.prenom3.text = p!!.prenom
        binding.image3.setImageURI(Uri.parse(p!!.imagePath))
        binding.age3.text = p!!.age.toString()
        binding.phone3.text = p!!.phone

    }
}