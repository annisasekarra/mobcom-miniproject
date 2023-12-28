package com.example.contactapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id :Int = 0,
    val name: String,
    val phone: String,
    val mobile: String,
    val email: String,
    val dateAdded: Long,

)