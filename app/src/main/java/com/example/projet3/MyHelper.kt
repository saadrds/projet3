package com.example.projet3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projet3.DBContract.Person.SQL_CREATE_TABLE
import com.example.projet3.DBContract.Person.SQL_DROP_TABLE


class myDBHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DROP_TABLE)
        onCreate(db)
    }

    companion object{
        const val DATABASE_NAME  = "Person.db"
        const val DATABASE_VERSION = 1
    }
}