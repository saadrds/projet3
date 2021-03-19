package com.example.projet3

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projet3.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val RESULT_LOAD_IMAGE = 22
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val  manager = DataManager(this)
        //setContentView(R.layout.activity_main)
        val imageView = binding.imageView2
        imageView.visibility = View.VISIBLE
       val  icoGallery = findViewById<Button>(R.id.image)
        icoGallery.setOnClickListener{
                val galleryIntent = Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryIntent.type = "image/*"
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
            }
        val save = binding.save
        save.setOnClickListener{
            //getting Data
            val nom = binding.nom.text.toString()
            val prenom = binding.prenom.text.toString()
            val age = binding.age.text.toString().toInt()
            val phone = binding.phone.text.toString()
            // Save the image in internal storage and get the uri
            val imageName = nom +"_" + prenom
            val imageNamePath = saveImageToInternalStorage(bitmap,imageName)

            //adding into database
            manager.add(nom, prenom,imageNamePath,age,phone)
            //clearing fields
            binding.nom.text.clear()
            binding.prenom.text.clear()
            binding.age.text.clear()
            binding.phone.text.clear()
            manager.close()
            Toast.makeText(this, "image Saved !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)


        }

        val afficher = binding.afficher
        afficher.setOnClickListener{
            manager.close()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            val uri: Uri = data.data!!
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            // Log.d(TAG, String.valueOf(bitmap));

            // Log.d(TAG, String.valueOf(bitmap));
            val imageView = findViewById<ImageView>(R.id.imageView2)
            imageView.setImageURI(uri)
            //imageView.setImageBitmap(bitmap)
            imageView.visibility = View.VISIBLE
        } else {
            Toast.makeText(this, "You have not selected and image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImageToInternalStorage(bitmap1 :Bitmap, uniqueName : String): String {
        // Get the image from drawable resource as drawable object

        // Get the bitmap from drawable object

        // Get the context wrapper instance
        val wrapper = ContextWrapper(applicationContext)

        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)


        // Create a file to save the image
        val fileName ="${uniqueName}.jpg"
        file = File(file, fileName)

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()

            // Close stream
            stream.close()
        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return file.absolutePath
    }
    lateinit var bitmap:Bitmap
}