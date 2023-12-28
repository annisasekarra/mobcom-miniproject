package com.example.contactapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contactapp.data.Contact

data class ContactState (
    val contacts: List<Contact> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val phone: MutableState<String> = mutableStateOf(""),
    val mobile: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf("")
)