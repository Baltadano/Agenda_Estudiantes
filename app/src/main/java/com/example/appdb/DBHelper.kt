package com.example.appdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory

    class DBHelper (context: Context, name: String, factory: CursorFactory?, version: Int): SQLiteOpenHelper(context, name,factory, version){

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("create table estudiantes (control int primary key, nombre text, carrera text, semestre text, grupo text)")
            //, semestre text, grupo text

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}