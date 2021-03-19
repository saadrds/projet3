package com.example.projet3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet3.databinding.ActivityMain2Binding
import com.example.projet3.databinding.ActivityMainBinding
//main activity 2 inherit from DescAdapter.Itemclick to use the method OnitemClick
class MainActivity2 : AppCompatActivity(),DescAdapter.ItemClicked {
    private lateinit var binding : ActivityMain2Binding
    var array = ArrayList<Person>()
    val  manager = DataManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = getIntent()
        val lv = binding.listR
        //getting all data using the data manager method "getAllItems"
        array = manager.getAllItems()
        //using adapter
        val adap = DescAdapter(this,array)
        lv.adapter = adap
        lv.layoutManager = LinearLayoutManager(this)

    }

    override fun OnitemClick(p: Person) {
        //we make an intent to ativity 3 and we send a person object that is parcelable
        val intent = Intent(this, MainActivity3::class.java)
        val myBundle = Bundle()
        myBundle.putParcelable("personSelected",p)
        intent.putExtras(myBundle)
        //Log.i("saadR",p.imagePath)
        startActivity(intent)


    }
}