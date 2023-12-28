package com.example.contactapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY dateAdded")
    fun getContactsOrderdByDateAdded(): Flow<List<Contact>>


    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getContactsOrderdByName(): Flow<List<Contact>>

}