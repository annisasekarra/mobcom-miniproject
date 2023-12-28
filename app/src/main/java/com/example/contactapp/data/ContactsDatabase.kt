package com.example.contactapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],
    version = 2
)
abstract class ContactsDatabase: RoomDatabase() {
    abstract val dao: ContactDao
}