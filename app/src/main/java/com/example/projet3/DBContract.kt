package com.example.projet3

import android.provider.BaseColumns

object DBContract {
    object Person :BaseColumns{
        const val TABLE_NAME = "Person"
        const val NOM = "nom"
        const val PRENOM = "prenom"
        const val IMAGEPATH = "imagePath"
        const val AGE = "age"
        const val PHONE = "phone"

        const val SQL_CREATE_TABLE = "CREATE TABLE $TABLE_NAME($NOM TEXT, $PRENOM TEXT, $IMAGEPATH TEXT, $AGE INTEGER, $PHONE TEXT)"
        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}