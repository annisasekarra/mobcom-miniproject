package com.example.contactapp.presentation

import com.example.contactapp.data.Contact

sealed interface ContactsEvent {
    object SortContacts: ContactsEvent

    data class DeleteContact(val contact: Contact): ContactsEvent

    data class SaveContact(
        val name: String,
        val phone: String,
        val mobile : String,
        val email : String
    ): ContactsEvent
}