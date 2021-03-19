package com.example.projet3

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DescAdapter(var context: Context, val des: ArrayList<Person>): RecyclerView.Adapter<DescAdapter.FilleHolder>() {
        //var myActivity = context as ItemClicked
    inner class FilleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val age : TextView = itemView.findViewById<TextView>(R.id.ageItem)
            val phoneText : TextView = itemView.findViewById<TextView>(R.id.phoneItem)
            val text1 :TextView = itemView.findViewById<TextView>(R.id.textView3)
            val text2 : TextView = itemView.findViewById<TextView>(R.id.textView4)
            val image : ImageView = itemView.findViewById<ImageView>(R.id.imageView)
            lateinit var path : String
            init{
                itemView.setOnClickListener {
                (context as ItemClicked).OnitemClick(Person(text1.text.toString(), text2.text.toString(),path, (age.text.toString()).toInt(), phoneText.text.toString()))
            }
        }
    }

    interface ItemClicked{
        fun OnitemClick(p:Person)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_description,
            parent,
            false
        )
        return FilleHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilleHolder, position: Int) {
        val currentDesc = des[position]
        holder.text1.text = currentDesc.nom
        holder.text2.text = currentDesc.prenom
        holder.path = currentDesc.imagePath
        holder.age.text = currentDesc.age.toString()
        holder.phoneText.text = currentDesc.phone
        val uri = Uri.parse(holder.path)
        //val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        holder.image.setImageURI(uri)

    }

    override fun getItemCount(): Int {
        return des.size
    }


}