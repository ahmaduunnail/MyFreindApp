package com.example.myfriendapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyFreinds::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun myFreindsDAO(): MyFreindsDAO

    companion object{
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase (context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MyFreindsAppDB").build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}