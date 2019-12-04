package com.example.mvvmrom.room

import android.app.Activity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(GroupData::class,StudentData::class),version = 3)
abstract class AppDatabase :RoomDatabase(){
    abstract fun studentDao():StudentDao

    companion object{
        private lateinit var database:AppDatabase

        fun init(context: Context){
                synchronized(this){
                    database= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "sdudyDb.db").build()
                }
        }
        fun getInstanse()= database
    }
}