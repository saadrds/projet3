package com.example.projet3

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.projet3.DBContract.Person.NOM

class DataManager(val context: Context) {


    val helper = myDBHelper(context)

    fun add(nom: String, prenom: String, imagePath: String,age: Int, phone : String) {
        val dataBase = helper.writableDatabase
        val values = ContentValues()
        values.put(DBContract.Person.NOM, nom)
        values.put(DBContract.Person.PRENOM, prenom)
        values.put(DBContract.Person.IMAGEPATH,imagePath)
        values.put(DBContract.Person.AGE, age)
        values.put(DBContract.Person.PHONE, phone)

        val rowAffected = dataBase?.insert(DBContract.Person.TABLE_NAME, null, values)
    }

    /*
    fun delete(nom: String) {
        val dataBase = helper.writableDatabase
        val selection = "${DBContract.Person.NOM} = ?"
        val selectionArgs = arrayOf("$nom")
        val deleteRows = dataBase.delete(DBContract.Person.TABLE_NAME, selection, selectionArgs)
    }
    */
    fun getAllItems(): ArrayList<Person> {
        val result = ArrayList<Person>()
        val dataBase = helper.readableDatabase

        val curosr = dataBase?.query(DBContract.Person.TABLE_NAME, null, null, null, null, null, null)
        while (curosr?.moveToNext()!!) {
            //val id = curosr.getInt(curosr.getColumnIndex(BaseColumns._ID))
            val nom = curosr.getString(curosr.getColumnIndex(DBContract.Person.NOM))
            val prenom = curosr.getString(curosr.getColumnIndex(DBContract.Person.PRENOM))
            val imagePath = curosr.getString(curosr.getColumnIndex(DBContract.Person.IMAGEPATH))
            val age = curosr.getInt(curosr.getColumnIndex(DBContract.Person.AGE))
            val phone = curosr.getString(curosr.getColumnIndex(DBContract.Person.PHONE))
            result.add(Person(nom, prenom,imagePath,age,phone))
        }
        return result
    }
    /*
    fun update(nom: String, prenom: String, imagePath : String) {
        val dataBase = helper.writableDatabase
        val selection = "${DBContract.Person.NOM} = ?"
        val selectionArgs = arrayOf("$NOM")
        val values = ContentValues()
        values.put(DBContract.Person.NOM, nom)
        values.put(DBContract.Person.PRENOM, prenom)
        values.put(DBContract.Person.IMAGEPATH, imagePath)
        val nbRowsUpdated = dataBase.update(DBContract.Person.TABLE_NAME, values, selection, selectionArgs)
    }
*/
    fun close() {
        helper.close();
    }
}