package com.example.myfriendapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyFreindsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahTeman(freinds: MyFreinds)

    @Query("SELECT * FROM MyFreinds")
    fun ambilSemuaTeman():LiveData<List<MyFreinds>>

}