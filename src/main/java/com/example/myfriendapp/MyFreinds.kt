package com.example.myfriendapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyFreinds (
    @PrimaryKey(autoGenerate = true)
    val temanID: Int? = null,
    val name: String,
    val gender: String,
    val email: String,
    val phone: String,
    val address: String
)